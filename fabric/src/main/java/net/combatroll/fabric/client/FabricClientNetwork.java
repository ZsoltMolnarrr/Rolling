package net.combatroll.fabric.client;

import net.combatroll.client.ClientNetwork;
import net.combatroll.network.Packets;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class FabricClientNetwork {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(Packets.RollAnimation.ID, (client, handler, buf, responseSender) -> {
            final var packet = Packets.RollAnimation.read(buf);
            ClientNetwork.handleRollAnimation(client, packet);
        });

        ClientPlayNetworking.registerGlobalReceiver(Packets.ConfigSync.ID, (client, handler, buf, responseSender) -> {
            var config = Packets.ConfigSync.read(buf);
            ClientNetwork.handleConfigSync(client, config);
        });
    }
}
