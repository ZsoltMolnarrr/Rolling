package net.combatroll.mixin;

import net.combatroll.api.EntityAttributes_CombatRoll;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityAttributes.class) // Low priority to be applied as last
public class EntityAttributesMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void static_tail_CombatRoll(CallbackInfo ci) {
        EntityAttributes_CombatRoll.all.forEach(EntityAttributes_CombatRoll.Entry::register);
    }
}
