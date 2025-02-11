package net.combat_roll.utils;

import net.combat_roll.CombatRollMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.List;

public class SoundHelper {
    public static List<String> soundKeys = List.of(
        "roll",
        "roll_cooldown_ready"
    );

    public static void registerSounds() {
        for (var soundKey: soundKeys) {
            var soundId = Identifier.of(CombatRollMod.ID, soundKey);
            var soundEvent = SoundEvent.of(soundId);
            Registry.register(Registries.SOUND_EVENT, soundId, soundEvent);
        }
    }
}
