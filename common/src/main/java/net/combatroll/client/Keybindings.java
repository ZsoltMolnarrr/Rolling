package net.combatroll.client;

import net.combatroll.CombatRollMod;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.List;

public class Keybindings {
    public static KeyBinding roll;
    public static List<KeyBinding> all;

    static {
        roll = new KeyBinding(
                "keybinds.combatroll.roll",
                InputUtil.Type.KEYSYM,
                InputUtil.GLFW_KEY_R,
                CombatRollMod.modName());

        all = List.of(roll);
    }
}
