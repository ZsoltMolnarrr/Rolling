package net.combat_roll.forge;

import net.minecraftforge.fml.ModList;
import net.combat_roll.Platform;

import static net.combat_roll.Platform.Type.FORGE;

public class PlatformImpl {
    public static Platform.Type getPlatformType() {
        return FORGE;
    }

    public static boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    public static Collection<ServerPlayerEntity> tracking(ServerPlayerEntity player) {
        return (Collection<ServerPlayerEntity>) player.getWorld().getPlayers(); // TODO
    }

    public static Collection<ServerPlayerEntity> around(ServerWorld world, Vec3d origin, double distance) {
        return world.getPlayers((player) -> player.getPos().squaredDistance(origin) <= (distance*distance));
    }
}
