package net.combatroll.client;

import com.google.gson.Gson;
import net.combatroll.CombatRollMod;
import net.combatroll.config.ServerConfig;
import net.combatroll.internals.RollingEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.combatroll.network.Packets;
import net.minecraft.network.PacketByteBuf;

public class ClientNetwork {
    public static void handleRollAnimation(MinecraftClient client, Packets.RollAnimation packet) {
        client.execute(() -> {
            var entity = client.world.getEntityById(packet.playerId());
            if (entity instanceof PlayerEntity player) {
                RollEffect.playVisuals(packet.visuals(), player, packet.velocity());
            }
        });
    }

    public static void handleConfigSync(MinecraftClient client, Packets.ConfigSync packet) {
        var rollingPlayer = ((RollingEntity)client.player);
        if (rollingPlayer != null) {
            rollingPlayer.getRollManager().isEnabled = true;
        }
        var gson = new Gson();
        var config = gson.fromJson(packet.json(), ServerConfig.class);
        CombatRollMod.config = config;
    }
}
