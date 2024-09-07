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

        ClientConfigurationNetworking.registerGlobalReceiver(Packets.ConfigSync.PACKET_ID, (packet, context) -> {
            ClientNetwork.handleConfigSync(packet);
            context.responseSender().sendPacket(new Packets.Ack(FabricServerNetwork.ConfigurationTask.name));
        });

        // Play stage

        ClientPlayNetworking.registerGlobalReceiver(Packets.RollAnimation.PACKET_ID, (packet, context) -> {
            ClientNetwork.handleRollAnimation(packet);
        });
    }
}
