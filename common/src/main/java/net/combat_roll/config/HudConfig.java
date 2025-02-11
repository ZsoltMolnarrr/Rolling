package net.combat_roll.config;

import net.combat_roll.client.gui.HudElement;
import net.minecraft.util.math.Vec2f;

public class HudConfig {
    public HudElement rollWidget;

    public static HudConfig createDefault() {
        var config = new HudConfig();
        config.rollWidget = createDefaultRollWidget();
        return config;
    }

    public static HudElement createDefaultRollWidget() {
        var origin = HudElement.Origin.BOTTOM;
        var offset = origin.initialOffset().add(new Vec2f(108, 0));
        return new HudElement(origin, offset);
    }
}
