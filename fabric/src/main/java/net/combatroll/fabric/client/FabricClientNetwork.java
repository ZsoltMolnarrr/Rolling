package net.combatroll.fabric.client;

import net.combatroll.client.ClientNetwork;
import net.combatroll.fabric.platform.FabricServerNetwork;
import net.combatroll.network.Packets;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

public class FabricClientNetwork {
    public static void init() {
        // Config stage
        ClientConfigurationNetworking.registerGlobalReceiver(Packets.ConfigSync.ID, (client, handler, buf, responseSender) -> {
            final var packet = Packets.ConfigSync.read(buf);
            ClientNetwork.handleConfigSync(packet);

            final var ack = new Packets.Ack(FabricServerNetwork.ConfigurationTask.name);
            final var ackBuffer = PacketByteBufs.create();
            ack.write(ackBuffer);
            responseSender.sendPacket(Packets.Ack.ID, ackBuffer);
        });

        // Play stage
        ClientPlayNetworking.registerGlobalReceiver(Packets.RollAnimation.ID, (client, handler, buf, responseSender) -> {
            final var packet = Packets.RollAnimation.read(buf);
            ClientNetwork.handleRollAnimation(packet);
        });
    }
}
