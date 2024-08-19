package net.combatroll.fabric.platform;

import net.combatroll.CombatRollMod;
import net.combatroll.network.Packets;
import net.combatroll.network.ServerNetwork;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayerConfigurationTask;

import java.util.function.Consumer;

public class FabricServerNetwork {
    public static void init() {
        // Config stage
        ServerConfigurationConnectionEvents.CONFIGURE.register((handler, server) -> {
            // This if block is required! Otherwise the client gets stuck in connection screen
            // if the client cannot handle the packet.
            if (ServerConfigurationNetworking.canSend(handler, Packets.ConfigSync.ID)) {
                handler.addTask(new ConfigurationTask(Packets.ConfigSync.serialize(CombatRollMod.config)));
            }
        });

        PayloadTypeRegistry.configurationC2S().register(Packets.Ack.PACKET_ID, Packets.Ack.CODEC);
        ServerConfigurationNetworking.registerGlobalReceiver(Packets.Ack.PACKET_ID, (packet, context) -> {
            if (packet.code().equals(ConfigurationTask.name)) {
                context.networkHandler().completeTask(ConfigurationTask.KEY);
            }
        });

        // Play stage
        PayloadTypeRegistry.playC2S().register(Packets.RollPublish.PACKET_ID, Packets.RollPublish.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(Packets.RollPublish.PACKET_ID, (packet, context) -> {
            ServerNetwork.handleRollPublish(packet, context.server(), context.player());
        });
    }

    public record ConfigurationTask(String configString) implements ServerPlayerConfigurationTask {
        public static final String name = CombatRollMod.ID + ":" + "config";
        public static final ServerPlayerConfigurationTask.Key KEY = new ServerPlayerConfigurationTask.Key(name);

        @Override
        public ServerPlayerConfigurationTask.Key getKey() {
            return KEY;
        }

        @Override
        public void sendPacket(Consumer<Packet<?>> sender) {
            sender.accept(ServerConfigurationNetworking.createS2CPacket(new Packets.ConfigSync(this.configString)));
        }
    }
}
