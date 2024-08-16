package net.combatroll.fabric;

import net.combatroll.CombatRollMod;
import net.combatroll.fabric.platform.FabricServerNetwork;
import net.fabricmc.api.ModInitializer;
import net.combatroll.utils.SoundHelper;

public class CombatRollModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CombatRollMod.init();
        SoundHelper.registerSounds();
        FabricServerNetwork.init();
    }
}