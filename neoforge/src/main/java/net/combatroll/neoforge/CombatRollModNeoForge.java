package net.combatroll.neoforge;

import net.combatroll.CombatRollMod;
import net.combatroll.utils.SoundHelper;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(CombatRollMod.ID)
public final class CombatRollModNeoForge {
    public CombatRollModNeoForge(IEventBus modEventBus) {
        CombatRollMod.init();
        SOUND_EVENTS.register(modEventBus);
    }

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, CombatRollMod.ID);

    static {
        SoundHelper.soundKeys.forEach(soundKey -> SOUND_EVENTS.register(soundKey, () -> SoundEvent.of(new Identifier(CombatRollMod.ID, soundKey))));
    }
}
