package net.combatroll.forge;

import net.combatroll.CombatRoll;
import net.combatroll.api.Enchantments_CombatRoll;
import net.combatroll.api.EntityAttributes_CombatRoll;
import net.combatroll.utils.SoundHelper;
import net.fabricmc.fabric.api.networking.v1.NetworkHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(CombatRoll.MOD_ID)
public class ForgeMod {

    public static DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CombatRoll.MOD_ID);

    public ForgeMod() {
        // EventBuses.registerModEventBus(Rolling.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CombatRoll.init();
        NetworkHandler.registerMessages();
        registerSounds();
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CombatRoll.configureEnchantments();
    }

    @SubscribeEvent
    public void register(RegisterEvent event) {
        // These don't seem to do anything :D
        event.register(ForgeRegistries.Keys.ATTRIBUTES,
            helper -> {
                helper.register(EntityAttributes_CombatRoll.distanceId, EntityAttributes_CombatRoll.DISTANCE);
                helper.register(EntityAttributes_CombatRoll.rechargeId, EntityAttributes_CombatRoll.RECHARGE);
                helper.register(EntityAttributes_CombatRoll.countId, EntityAttributes_CombatRoll.COUNT);
            }
        );
        event.register(ForgeRegistries.Keys.ENCHANTMENTS,
            helper -> {
                CombatRoll.configureEnchantments();
                helper.register(Enchantments_CombatRoll.distanceId, Enchantments_CombatRoll.DISTANCE);
                helper.register(Enchantments_CombatRoll.rechargeId, Enchantments_CombatRoll.RECHARGE);
                helper.register(Enchantments_CombatRoll.countId, Enchantments_CombatRoll.COUNT);
            }
        );
    }

    private void registerSounds() {
        for (var soundKey: SoundHelper.soundKeys) {
            SOUNDS.register(soundKey, () -> SoundEvent.of(Identifier.of(CombatRoll.MOD_ID, soundKey)));
        }
    }
}