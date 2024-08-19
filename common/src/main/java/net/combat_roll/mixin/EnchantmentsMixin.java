package net.combat_roll.mixin;

import net.combat_roll.CombatRollMod;
import net.minecraft.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Enchantments.class)
public class EnchantmentsMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void static_tail_combat_roll(CallbackInfo ci) {
        CombatRollMod.registerEnchantments();
    }
}