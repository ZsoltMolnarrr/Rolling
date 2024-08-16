package net.combatroll.forge;

import net.minecraftforge.fml.ModList;
import net.combatroll.Platform;

import static net.combatroll.Platform.Type.FORGE;

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
