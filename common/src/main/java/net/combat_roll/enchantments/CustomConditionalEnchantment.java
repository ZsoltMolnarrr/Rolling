package net.combat_roll.enchantments;

import net.minecraft.item.ItemStack;

public interface CustomConditionalEnchantment {
    interface Condition {
        boolean isAcceptableItem(ItemStack stack);
    }
    void setCondition(Condition condition);
}
