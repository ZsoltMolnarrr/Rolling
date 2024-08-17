package net.combatroll.network;

import com.google.gson.Gson;
import net.combatroll.CombatRollMod;
import net.combatroll.config.ServerConfig;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.combatroll.client.RollEffect;

public class Packets {
    public record RollPublish(int playerId, RollEffect.Visuals visuals, Vec3d velocity) implements CustomPayload {
        public static Identifier ID = new Identifier(CombatRollMod.ID, "publish");

        public static RollPublish read(PacketByteBuf buffer) {
            int playerId = buffer.readInt();
            var visuals = new RollEffect.Visuals(
                    buffer.readString(),
                    RollEffect.Particles.valueOf(buffer.readString()));
            Vec3d velocity = new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
            return new RollPublish(playerId, visuals, velocity);
        }

        @Override
        public void write(PacketByteBuf buffer) {
            buffer.writeInt(playerId);
            buffer.writeString(visuals.animationName());
            buffer.writeString(visuals.particles().toString());
            buffer.writeDouble(velocity.x);
            buffer.writeDouble(velocity.y);
            buffer.writeDouble(velocity.z);
        }

        @Override
        public Identifier id() {
            return ID;
        }
    }

    public record RollAnimation(int playerId, RollEffect.Visuals visuals, Vec3d velocity) implements CustomPayload {
        public static Identifier ID = new Identifier(CombatRollMod.ID, "animation");

        public static RollAnimation read(PacketByteBuf buffer) {
            int playerId = buffer.readInt();
            var visuals = new RollEffect.Visuals(
                    buffer.readString(),
                    RollEffect.Particles.valueOf(buffer.readString()));
            Vec3d velocity = new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
            return new RollAnimation(playerId, visuals, velocity);
        }

        @Override
        public void write(PacketByteBuf buffer) {
            buffer.writeInt(playerId);
            buffer.writeString(visuals.animationName());
            buffer.writeString(visuals.particles().toString());
            buffer.writeDouble(velocity.x);
            buffer.writeDouble(velocity.y);
            buffer.writeDouble(velocity.z);
        }

        @Override
        public Identifier id() {
            return null;
        }
    }

    public record ConfigSync(String json) implements CustomPayload {
        public static Identifier ID = new Identifier(CombatRollMod.ID, "config_sync");

        public static String serialize(ServerConfig config) {
            var gson = new Gson();
            return gson.toJson(config);
        }

        @Override
        public void write(PacketByteBuf buffer) {
            buffer.writeString(json);
        }

        public static ConfigSync read(PacketByteBuf buffer) {
            var gson = new Gson();
            var json = buffer.readString();
            return new ConfigSync(json);
        }

        @Override
        public Identifier id() {
            return ID;
        }
    }
}
