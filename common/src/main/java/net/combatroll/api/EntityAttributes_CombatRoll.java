package net.combatroll.api;

import net.combatroll.CombatRollMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EntityAttributes_CombatRoll {

    public static final String NAMESPACE = CombatRollMod.ID;
    public static final ArrayList<Entry> all = new ArrayList<>();
    private static Entry entry(String name, double baseValue, double minValue, double maxValue, boolean tracked) {
        var entry = new Entry(name, baseValue, minValue, maxValue, tracked);
        all.add(entry);
        return entry;
    }

    public static class Entry {
        public final Identifier id;
        public final String translationKey;
        public final EntityAttribute attribute;
        public final double baseValue;
        @Nullable
        public RegistryEntry<EntityAttribute> entry;

        public Entry(String name, double baseValue, double minValue, double maxValue, boolean tracked) {
            this.id = Identifier.of(NAMESPACE, name);
            this.translationKey = "attribute.name." + "combat_roll" + "." + name;
            this.attribute = new ClampedEntityAttribute(translationKey, baseValue, minValue, maxValue).setTracked(tracked);
            this.baseValue = baseValue;
        }

        public double asMultiplier(double attributeValue) {
            return attributeValue / baseValue;
        }

        public void register() {
            entry = Registry.registerReference(Registries.ATTRIBUTE, id, attribute);
        }
    }




//    public static final String distanceName = "distance";
//    public static final Identifier distanceId = Identifier.of(CombatRollMod.ID + ":" + distanceName);
//    public static final EntityAttribute DISTANCE = (new ClampedEntityAttribute("attribute.name.combat_roll." + distanceName, 3.0, 1, 24.0)).setTracked(true);
//
//    public static final String rechargeName = "recharge";
//    public static final Identifier rechargeId = Identifier.of(CombatRollMod.ID + ":" + rechargeName);
//    public static final EntityAttribute RECHARGE = (new ClampedEntityAttribute("attribute.name.combat_roll." + rechargeName, 20, 0.1, 200)).setTracked(true);
//
//    public static final String countName = "count";
//    public static final Identifier countId = Identifier.of(CombatRollMod.ID + ":" + countName);
//    public static final EntityAttribute COUNT = (new ClampedEntityAttribute("attribute.name.combat_roll." + countName, 1, 0, 20.0)).setTracked(true);

    public static final Entry DISTANCE = entry("distance", 3.0, 1, 24.0, true);
    public static final Entry RECHARGE = entry("recharge", 20, 0.1, 200, true);
    public static final Entry COUNT = entry("count", 1, 0, 20.0, true);

//    public static List<EntityAttribute> all;
//    static {
//        all = List.of(DISTANCE, RECHARGE, COUNT);
//    }

    // Helper

    public enum Type {
        DISTANCE, RECHARGE, COUNT
    }

    public static double getAttributeValue(PlayerEntity player, Type type) {
        switch (type) {
            case DISTANCE -> {
                var value = player.getAttributeValue(DISTANCE.entry);
//                var level = EnchantmentHelper.getEquipmentLevel(Enchantments_CombatRoll.DISTANCE, player);
//                value = Enchantments_CombatRoll.DISTANCE.apply(value, level);
                return value;
            }
            case RECHARGE -> {
                var value = player.getAttributeValue(RECHARGE.entry);
//                var chestLevel = EnchantmentHelper.getEquipmentLevel(Enchantments_CombatRoll.RECHARGE, player);
//                value = Enchantments_CombatRoll.RECHARGE.apply(value, chestLevel);
                return value;
            }
            case COUNT -> {
                var value = player.getAttributeValue(COUNT.entry);
//                var level = EnchantmentHelper.getEquipmentLevel(Enchantments_CombatRoll.COUNT, player);
//                value = Enchantments_CombatRoll.COUNT.apply(value, level);
                return value;
            }
        }
        return 1; // Should never happen
    }
}
