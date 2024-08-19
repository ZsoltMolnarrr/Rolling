package net.combat_roll;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.Collection;

public class Platform {
    public static final boolean Fabric;
    public static final boolean Forge;
    public static final boolean NeoForge;

    static
    {
        Fabric = getPlatformType() == Type.FABRIC;
        Forge  = getPlatformType() == Type.FORGE;
        NeoForge = getPlatformType() == Type.NEOFORGE;
    }

    public enum Type { FABRIC, FORGE, NEOFORGE }

    @ExpectPlatform
    protected static Type getPlatformType() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modid) {
        throw new AssertionError();
    }

    // MARK: Network hooks

    @ExpectPlatform
    public static Collection<ServerPlayerEntity> tracking(ServerPlayerEntity player) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Collection<ServerPlayerEntity> around(ServerWorld world, Vec3d origin, double distance) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean networkS2C_CanSend(ServerPlayerEntity player, CustomPayload.Id<?> packetId) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void networkS2C_Send(ServerPlayerEntity player, CustomPayload payload) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void networkC2S_Send(CustomPayload payload) {
        throw new AssertionError();
    }
}
