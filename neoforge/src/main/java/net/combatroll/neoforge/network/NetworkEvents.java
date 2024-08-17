package net.combatroll.neoforge.network;

import com.google.gson.Gson;
import net.combatroll.CombatRollMod;
import net.combatroll.client.ClientNetwork;
import net.combatroll.network.Packets;
import net.combatroll.network.ServerNetwork;
import net.minecraft.network.listener.ServerConfigurationPacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerConfigurationTask;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.configuration.ICustomConfigurationTask;
import net.neoforged.neoforge.network.event.OnGameConfigurationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = CombatRollMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkEvents {
    @SubscribeEvent
    public static void register(final OnGameConfigurationEvent event) {
        event.register(new ConfigurationTask(event.getListener()));
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(CombatRollMod.ID);

        // Server

        registrar.play(Packets.RollPublish.ID, Packets.RollPublish::read, handler -> {
            handler.server((packet, context) -> {
                var player = (ServerPlayerEntity)context.player().get();
                var server = player.server;
                ServerNetwork.handleRollPublish(packet, server, player);
            });
        });

        // Client

        registrar.configuration(Packets.ConfigSync.ID, Packets.ConfigSync::read, handler -> {
            handler.client((packet, context) -> {
                ClientNetwork.handleConfigSync(packet);
            });
        });

        registrar.play(Packets.RollAnimation.ID, Packets.RollAnimation::read, handler -> {
            handler.client((packet, context) -> {
                ClientNetwork.handleRollAnimation(packet);
            });
        });
    }

    public record ConfigurationTask(ServerConfigurationPacketListener listener) implements ICustomConfigurationTask {
        public static final ServerPlayerConfigurationTask.Key KEY = new ServerPlayerConfigurationTask.Key(new Identifier(CombatRollMod.ID, "config"));
        @Override
        public void run(Consumer<CustomPayload> sender) {
            var gson = new Gson();
            var configString = gson.toJson(CombatRollMod.config);
            var configPayload = new Packets.ConfigSync(configString);
            sender.accept(configPayload);
            listener.onTaskFinished(KEY);
        }

        @Override
        public Key getKey() {
            return KEY;
        }
    }
}
