package net.combat_roll.network;

import com.google.gson.Gson;
import net.combat_roll.CombatRollMod;
import net.combat_roll.client.RollEffect;
import net.combat_roll.config.ServerConfig;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class Packets {
    public record RollPublish(int playerId, RollEffect.Visuals visuals, Vec3d velocity) implements CustomPayload {
        public static final Identifier ID = Identifier.of(CombatRollMod.ID, "publish");
        public static final CustomPayload.Id<RollPublish> PACKET_ID = new CustomPayload.Id<>(ID);
        public static final PacketCodec<RegistryByteBuf, RollPublish> CODEC = PacketCodec.of(RollPublish::write, RollPublish::read);

        public static RollPublish read(RegistryByteBuf buffer) {
            int playerId = buffer.readInt();
            var visuals = new RollEffect.Visuals(
                    buffer.readString(),
                    RollEffect.Particles.valueOf(buffer.readString()));
            Vec3d velocity = new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
            return new RollPublish(playerId, visuals, velocity);
        }

        public void write(RegistryByteBuf buffer) {
            buffer.writeInt(playerId);
            buffer.writeString(visuals.animationName());
            buffer.writeString(visuals.particles().toString());
            buffer.writeDouble(velocity.x);
            buffer.writeDouble(velocity.y);
            buffer.writeDouble(velocity.z);
        }

        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }
    }

    public record RollAnimation(int playerId, RollEffect.Visuals visuals, Vec3d velocity) implements CustomPayload {
        public static final Identifier ID = Identifier.of(CombatRollMod.ID, "animation");
        public static final CustomPayload.Id<RollAnimation> PACKET_ID = new CustomPayload.Id<>(ID);
        public static final PacketCodec<RegistryByteBuf, RollAnimation> CODEC = PacketCodec.of(RollAnimation::write, RollAnimation::read);

        public static RollAnimation read(RegistryByteBuf buffer) {
            int playerId = buffer.readInt();
            var visuals = new RollEffect.Visuals(
                    buffer.readString(),
                    RollEffect.Particles.valueOf(buffer.readString()));
            Vec3d velocity = new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
            return new RollAnimation(playerId, visuals, velocity);
        }

        public void write(RegistryByteBuf buffer) {
            buffer.writeInt(playerId);
            buffer.writeString(visuals.animationName());
            buffer.writeString(visuals.particles().toString());
            buffer.writeDouble(velocity.x);
            buffer.writeDouble(velocity.y);
            buffer.writeDouble(velocity.z);
        }

        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }
    }

    public record ConfigSync(String json) implements CustomPayload {
        public static final Identifier ID = Identifier.of(CombatRollMod.ID, "config_sync");
        public static final CustomPayload.Id<ConfigSync> PACKET_ID = new CustomPayload.Id<>(ID);
        public static final PacketCodec<PacketByteBuf, ConfigSync> CODEC = PacketCodec.of(ConfigSync::write, ConfigSync::read);

        private static final Gson gson = new Gson();
        public static String serialize(ServerConfig config) {
            return gson.toJson(config);
        }

        public void write(PacketByteBuf buffer) {
            buffer.writeString(json);
        }

        public static ConfigSync read(PacketByteBuf buffer) {
            var json = buffer.readString();
            return new ConfigSync(json);
        }

        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }
    }

    public record Ack(String code) implements CustomPayload {
        public static final Identifier ID = Identifier.of(CombatRollMod.ID, "ack");
        public static final CustomPayload.Id<Ack> PACKET_ID = new CustomPayload.Id<>(ID);
        public static final PacketCodec<PacketByteBuf, Ack> CODEC = PacketCodec.of(Ack::write, Ack::read);

        public void write(PacketByteBuf buffer) {
            buffer.writeString(code);
        }

        public static Ack read(PacketByteBuf buffer) {
            var code = buffer.readString();
            return new Ack(code);
        }

        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }
    }
}
