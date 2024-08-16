package net.combatroll.network;

import com.google.common.collect.Iterables;
import net.combatroll.CombatRollMod;
import net.combatroll.Platform;
import net.combatroll.api.RollInvulnerable;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.combatroll.api.event.Event;
import net.combatroll.api.event.ServerSideRollEvents;

public class ServerNetwork {
    public static String configSerialized = "";

    public static void initializeHandlers() {
        configSerialized = Packets.ConfigSync.serialize(CombatRollMod.config);

//        ServerPlayNetworking.registerGlobalReceiver(Packets.RollPublish.ID, (server, player, handler, buf, responseSender) -> {
//            ServerWorld world = Iterables.tryFind(server.getWorlds(), (element) -> element == player.getWorld())
//                    .orNull();
//            if (world == null || world.isClient) {
//                return;
//            }
//            final var packet = Packets.RollPublish.read(buf);
//            final var velocity = packet.velocity();
//            final var forwardPacket = new Packets.RollAnimation(player.getId(), packet.visuals(), packet.velocity());
//            PlayerLookup.tracking(player).forEach(serverPlayer -> {
//                try {
//                    if (serverPlayer.getId() != player.getId() && ServerPlayNetworking.canSend(serverPlayer, Packets.RollAnimation.ID)) {
//                        ServerPlayNetworking.send(serverPlayer, Packets.RollAnimation.ID, forwardPacket.write());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//
//            world.getServer().executeSync(() -> {
//                ((RollInvulnerable)player).setRollInvulnerableTicks(CombatRollMod.config.invulnerable_ticks_upon_roll);
//                player.addExhaustion(CombatRollMod.config.exhaust_on_roll);
//                var proxy = (Event.Proxy<ServerSideRollEvents.PlayerStartRolling>)ServerSideRollEvents.PLAYER_START_ROLLING;
//                proxy.handlers.forEach(hander -> { hander.onPlayerStartedRolling(player, velocity);});
//            });
//        });
    }

    public static void handleRollPublish(Packets.RollPublish packet, MinecraftServer server, ServerPlayerEntity player) {
        ServerWorld world = Iterables.tryFind(server.getWorlds(), (element) -> element == player.getWorld())
                .orNull();
        final var velocity = packet.velocity();
        final var forwardPacket = new Packets.RollAnimation(player.getId(), packet.visuals(), packet.velocity());
        Platform.tracking(player).forEach(serverPlayer -> {
            try {
                if (serverPlayer.getId() != player.getId() && Platform.networkS2C_CanSend(serverPlayer, Packets.RollAnimation.ID)) {
                    Platform.networkS2C_Send(serverPlayer, Packets.RollAnimation.ID, forwardPacket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        world.getServer().executeSync(() -> {
            ((RollInvulnerable)player).setRollInvulnerableTicks(CombatRollMod.config.invulnerable_ticks_upon_roll);
            player.addExhaustion(CombatRollMod.config.exhaust_on_roll);
            var proxy = (Event.Proxy<ServerSideRollEvents.PlayerStartRolling>)ServerSideRollEvents.PLAYER_START_ROLLING;
            proxy.handlers.forEach(hander -> { hander.onPlayerStartedRolling(player, velocity);});
        });
    }
}
