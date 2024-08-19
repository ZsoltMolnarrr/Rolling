package net.combat_roll.fabric.client;

import net.combat_roll.client.ClientNetwork;
import net.combat_roll.fabric.platform.FabricServerNetwork;
import net.combat_roll.network.Packets;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class FabricClientNetwork {
    public static void init() {
        // Config stage
        PayloadTypeRegistry.configurationS2C().register(Packets.ConfigSync.PACKET_ID, Packets.ConfigSync.CODEC);
        ClientConfigurationNetworking.registerGlobalReceiver(Packets.ConfigSync.PACKET_ID, (packet, context) -> {
            ClientNetwork.handleConfigSync(packet);
            context.responseSender().sendPacket(new Packets.Ack(FabricServerNetwork.ConfigurationTask.name));
        });

        // Play stage
        PayloadTypeRegistry.playS2C().register(Packets.RollAnimation.PACKET_ID, Packets.RollAnimation.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(Packets.RollAnimation.PACKET_ID, (packet, context) -> {
            ClientNetwork.handleRollAnimation(packet);
        });
    }
}
