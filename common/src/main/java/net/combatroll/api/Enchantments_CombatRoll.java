package net.combatroll.api;

import net.combatroll.CombatRollMod;
import net.combatroll.enchantments.AmplifierEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;

import static net.combatroll.enchantments.AmplifierEnchantment.Operation.ADD;
import static net.combatroll.enchantments.AmplifierEnchantment.Operation.MULTIPLY;
import static net.minecraft.enchantment.EnchantmentTarget.*;

public class Enchantments_CombatRoll {
    // Longfooted
    public static final String distanceName = "longfooted";
    public static final Identifier distanceId = new Identifier(CombatRollMod.ID + ":" + distanceName);
    public static final AmplifierEnchantment DISTANCE = new AmplifierEnchantment(
            Enchantment.Rarity.RARE,
            ADD,
            CombatRollMod.enchantmentConfig.value.longfooted,
            ARMOR_FEET,
            new EquipmentSlot[]{ EquipmentSlot.FEET });

    // Acrobat
    public static final String rechargeName = "acrobat";
    public static final Identifier rechargeId = new Identifier(CombatRollMod.ID + ":" + rechargeName);
    public static final AmplifierEnchantment RECHARGE = new AmplifierEnchantment(
            Enchantment.Rarity.RARE,
            MULTIPLY,
            CombatRollMod.enchantmentConfig.value.acrobat,
            WEARABLE,
            new EquipmentSlot[]{ EquipmentSlot.CHEST, EquipmentSlot.LEGS })
            .condition(stack ->
                    ARMOR_CHEST.isAcceptableItem(stack.getItem())
                    || ARMOR_LEGS.isAcceptableItem(stack.getItem())
            );

    // Multi-Roll
    public static final String countName = "multi_roll";
    public static final Identifier countId = new Identifier(CombatRollMod.ID + ":" + countName);
    public static final AmplifierEnchantment COUNT = new AmplifierEnchantment(
            Enchantment.Rarity.VERY_RARE,
            ADD,
            CombatRollMod.enchantmentConfig.value.multi_roll,
            ARMOR_HEAD,
            new EquipmentSlot[]{ EquipmentSlot.HEAD });
}
