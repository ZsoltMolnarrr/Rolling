package net.combatroll.client;

import com.google.gson.Gson;
import net.combatroll.CombatRollMod;
import net.combatroll.config.ServerConfig;
import net.combatroll.internals.RollingEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.combatroll.network.Packets;

public class ClientNetwork {
    public static void handleRollAnimation(Packets.RollAnimation packet) {
        var client = MinecraftClient.getInstance(); // NeoForge network handler context doesn't provide a client instance
        client.execute(() -> {
            var entity = client.world.getEntityById(packet.playerId());
            if (entity instanceof PlayerEntity player) {
                RollEffect.playVisuals(packet.visuals(), player, packet.velocity());
            }
        });
    }

    public static void handleConfigSync(Packets.ConfigSync packet) {
        var client = MinecraftClient.getInstance(); // NeoForge network handler context doesn't provide a client instance
        var rollingPlayer = ((RollingEntity)client.player);
        if (rollingPlayer != null) {
            rollingPlayer.getRollManager().isEnabled = true;
        }
        var gson = new Gson();
        var config = gson.fromJson(packet.json(), ServerConfig.class);
        CombatRollMod.config = config;
    }
}
