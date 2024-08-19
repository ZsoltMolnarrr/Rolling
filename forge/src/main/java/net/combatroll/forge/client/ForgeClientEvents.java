package net.combat_roll.forge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.combat_roll.combat_roll;
import net.combat_roll.client.gui.HudRenderHelper;

@Mod.EventBusSubscriber(modid = combat_roll.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {
    @SubscribeEvent
    public static void onRenderHud(RenderGuiEvent.Post event){
        HudRenderHelper.render(event.getGuiGraphics(), event.getPartialTick());
    }
}
