package net.combat_roll.neoforge.client;

import net.combat_roll.CombatRollMod;
import net.combat_roll.client.gui.HudRenderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@Mod.EventBusSubscriber(modid = CombatRollMod.ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventsNeoForge {
    @SubscribeEvent
    public static void onRenderHud(RenderGuiEvent.Post event){
        HudRenderHelper.render(event.getGuiGraphics(), event.getPartialTick());
    }
}
