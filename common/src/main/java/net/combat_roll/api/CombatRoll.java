package net.combat_roll.api;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CombatRoll {
    public static final String NAMESPACE = "combat_roll";

    public static class Attributes {
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

        public static final Entry DISTANCE = entry("distance", 3.0, 1, 24.0, true);
        public static final Entry RECHARGE = entry("recharge", 20, 0.1, 200, true);
        public static final Entry COUNT = entry("count", 1, 0, 20.0, true);
    }
}
