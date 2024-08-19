package net.combat_roll.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
//    @Inject(method = "getPossibleEntries", at = @At("RETURN"))
//    private static void getPossibleEntries_Return(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
//        var entries = cir.getReturnValue();
//        var toRemove = new ArrayList<EnchantmentLevelEntry>();
//        for (var entry: entries) {
//            var enchantment = entry.enchantment;
//            if (enchantment instanceof CustomConditionalEnchantment) {
//                if (!entry.enchantment.isAcceptableItem(stack)) {
//                    toRemove.add(entry);
//                }
//            }
//        }
//        entries.removeAll(toRemove);
//    }
}
