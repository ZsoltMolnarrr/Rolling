package net.combat_roll.forge;

import net.combat_roll.combat_roll;
import net.combat_roll.api.Enchantments_combat_roll;
import net.combat_roll.api.EntityAttributes_combat_roll;
import net.combat_roll.utils.SoundHelper;
import net.fabricmc.fabric.api.networking.v1.NetworkHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(combat_roll.MOD_ID)
public class ForgeMod {

    public static DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, combat_roll.MOD_ID);

    public ForgeMod() {
        // EventBuses.registerModEventBus(Rolling.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        combat_roll.init();
        NetworkHandler.registerMessages();
        registerSounds();
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        combat_roll.configureEnchantments();
    }

    @SubscribeEvent
    public void register(RegisterEvent event) {
        // These don't seem to do anything :D
        event.register(ForgeRegistries.Keys.ATTRIBUTES,
            helper -> {
                helper.register(EntityAttributes_combat_roll.distanceId, EntityAttributes_combat_roll.DISTANCE);
                helper.register(EntityAttributes_combat_roll.rechargeId, EntityAttributes_combat_roll.RECHARGE);
                helper.register(EntityAttributes_combat_roll.countId, EntityAttributes_combat_roll.COUNT);
            }
        );
        event.register(ForgeRegistries.Keys.ENCHANTMENTS,
            helper -> {
                combat_roll.configureEnchantments();
                helper.register(Enchantments_combat_roll.distanceId, Enchantments_combat_roll.DISTANCE);
                helper.register(Enchantments_combat_roll.rechargeId, Enchantments_combat_roll.RECHARGE);
                helper.register(Enchantments_combat_roll.countId, Enchantments_combat_roll.COUNT);
            }
        );
    }

    private void registerSounds() {
        for (var soundKey: SoundHelper.soundKeys) {
            SOUNDS.register(soundKey, () -> SoundEvent.of(Identifier.of(combat_roll.MOD_ID, soundKey)));
        }
    }
}