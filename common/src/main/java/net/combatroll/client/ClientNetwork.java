package net.combatroll.client;

import net.combatroll.CombatRoll;
import net.combatroll.internals.RollingEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.combatroll.network.Packets;

public class ClientNetwork {
    public static void initializeHandlers() {
        ClientPlayNetworking.registerGlobalReceiver(Packets.RollAnimation.ID, (client, handler, buf, responseSender) -> {
            final var packet = Packets.RollAnimation.read(buf);
            client.execute(() -> {
                var entity = client.world.getEntityById(packet.playerId());
                if (entity instanceof PlayerEntity player) {
                    RollEffect.playVisuals(packet.visuals(), player, packet.velocity());
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(Packets.ConfigSync.ID, (client, handler, buf, responseSender) -> {
            var config = Packets.ConfigSync.read(buf);
            var rollingPlayer = ((RollingEntity)client.player);
            if (rollingPlayer != null) {
                rollingPlayer.getRollManager().isEnabled = true;
            }
            CombatRoll.config = config;
        });
    }
}
