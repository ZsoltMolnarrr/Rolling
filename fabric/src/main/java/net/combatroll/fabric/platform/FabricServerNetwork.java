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
        ServerConfigurationNetworking.registerGlobalReceiver(Packets.Ack.ID, (server, handler, buf, responseSender) -> {
            var packet = Packets.Ack.read(buf);
            // Warning: if you do not call completeTask, the client gets stuck!
            if (packet.code().equals(ConfigurationTask.name)) {
                handler.completeTask(ConfigurationTask.KEY);
            }
        });

        // Play stage
        ServerPlayNetworking.registerGlobalReceiver(Packets.RollPublish.ID, (server, player, handler, buf, responseSender) -> {
            var packet = Packets.RollPublish.read(buf);
            ServerNetwork.handleRollPublish(packet, server, player);
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
            var buffer = PacketByteBufs.create();
            new Packets.ConfigSync(this.configString).write(buffer);
            sender.accept(ServerConfigurationNetworking.createS2CPacket(Packets.ConfigSync.ID, buffer));
        }
    }
}
