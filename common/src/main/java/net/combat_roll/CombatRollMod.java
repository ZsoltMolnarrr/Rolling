package net.combat_roll;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.combat_roll.api.CombatRoll;
import net.combat_roll.config.EnchantmentsConfig;
import net.combat_roll.config.ServerConfig;
import net.combat_roll.config.ServerConfigWrapper;
import net.combat_roll.network.ServerNetwork;
import net.minecraft.client.resource.language.I18n;
import net.tinyconfig.ConfigManager;

public class CombatRollMod {
    public static final String ID = CombatRoll.NAMESPACE;
    public static String modName() {
        return I18n.translate(CombatRoll.NAMESPACE + ".mod_name");
    }

    public static ServerConfig config;
    public static ConfigManager<EnchantmentsConfig> enchantmentConfig = new ConfigManager<EnchantmentsConfig>
            ("enchantments", new EnchantmentsConfig())
            .builder()
            .setDirectory(CombatRollMod.ID)
            .sanitize(true)
            .build();


    public static void init() {
        AutoConfig.register(ServerConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        config = AutoConfig.getConfigHolder(ServerConfigWrapper.class).getConfig().server;
        enchantmentConfig.refresh();
        ServerNetwork.initializeHandlers();
        CombatRollMod.configureEnchantments();
    }

    public static void configureEnchantments() {
        var config = enchantmentConfig.value;
//        Enchantments_combat_roll.DISTANCE.properties = config.longfooted;
//        Enchantments_combat_roll.RECHARGE.properties = config.acrobat;
//        Enchantments_combat_roll.COUNT.properties = config.multi_roll;
    }

    public static void registerEnchantments() {
//        Registry.register(Registries.ENCHANTMENT, Enchantments_combat_roll.distanceId, Enchantments_combat_roll.DISTANCE);
//        Registry.register(Registries.ENCHANTMENT, Enchantments_combat_roll.rechargeId, Enchantments_combat_roll.RECHARGE);
//        Registry.register(Registries.ENCHANTMENT, Enchantments_combat_roll.countId, Enchantments_combat_roll.COUNT);
    }
}