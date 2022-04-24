package top.whitecola.kateclient.keybinds;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class HypixelGameSelectorKeybind {
    private static KeyBinding HypixelGameSelectorKeybind = new KeyBinding("key.kateclient.showhypixelgamemenu", Keyboard.KEY_I, "key.categories.kateclient");

    private HypixelGameSelectorKeybind(){}

    public static KeyBinding getInstance() {
        return HypixelGameSelectorKeybind;
    }
}
