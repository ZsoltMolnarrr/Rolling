package net.combatroll.api;

import net.combatroll.CombatRollMod;
import net.minecraft.util.Identifier;

public class Enchantments_CombatRoll {
    // Longfooted
    public static final String distanceName = "longfooted";
    public static final Identifier distanceId = Identifier.of(CombatRollMod.ID + ":" + distanceName);
//    public static final AmplifierEnchantment DISTANCE = new AmplifierEnchantment(
//            Enchantment.Rarity.RARE,
//            ADD,
//            CombatRollMod.enchantmentConfig.value.longfooted,
//            ARMOR_FEET,
//            new EquipmentSlot[]{ EquipmentSlot.FEET });

    // Acrobat
    public static final String rechargeName = "acrobat";
    public static final Identifier rechargeId = Identifier.of(CombatRollMod.ID + ":" + rechargeName);
//    public static final AmplifierEnchantment RECHARGE = new AmplifierEnchantment(
//            Enchantment.Rarity.RARE,
//            MULTIPLY,
//            CombatRollMod.enchantmentConfig.value.acrobat,
//            WEARABLE,
//            new EquipmentSlot[]{ EquipmentSlot.CHEST, EquipmentSlot.LEGS })
//            .condition(stack ->
//                    ARMOR_CHEST.isAcceptableItem(stack.getItem())
//                    || ARMOR_LEGS.isAcceptableItem(stack.getItem())
//            );

    // Multi-Roll
    public static final String countName = "multi_roll";
    public static final Identifier countId = Identifier.of(CombatRollMod.ID + ":" + countName);
//    public static final AmplifierEnchantment COUNT = new AmplifierEnchantment(
//            Enchantment.Rarity.VERY_RARE,
//            ADD,
//            CombatRollMod.enchantmentConfig.value.multi_roll,
//            ARMOR_HEAD,
//            new EquipmentSlot[]{ EquipmentSlot.HEAD });
}
