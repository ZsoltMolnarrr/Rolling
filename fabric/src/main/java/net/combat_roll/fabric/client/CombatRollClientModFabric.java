package net.combat_roll.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.combat_roll.client.CombatRollClient;
import net.combat_roll.client.Keybindings;
import net.combat_roll.client.gui.HudRenderHelper;

public class CombatRollClientModFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CombatRollClient.initialize();
        for(var keybinding: Keybindings.all) {
            KeyBindingHelper.registerKeyBinding(keybinding);
        }

        HudRenderCallback.EVENT.register((context, counter) -> {
            HudRenderHelper.render(context, counter.getTickDelta(true));
        });

        FabricClientNetwork.init();
    }
}
