package net.combatroll.neoforge;

import net.combatroll.CombatRollMod;
import net.combatroll.network.Packets;
import net.combatroll.network.ServerNetwork;
import net.minecraft.server.network.ServerPlayerEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod(CombatRollMod.ID)
public final class CombatRollModNeoForge {
    public CombatRollModNeoForge() {
        CombatRollMod.init();
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(CombatRollMod.ID);
        registrar.play(Packets.RollPublish.ID, Packets.RollPublish::read, handler -> {
            handler.server((packet, context) -> {
                var player = (ServerPlayerEntity)context.player().get();
                var server = player.server;
                ServerNetwork.handleRollPublish(packet, server, player);
            });
        });
    }
}
