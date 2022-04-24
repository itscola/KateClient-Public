package top.whitecola.kateclient.keybinds;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class MainMenuInGameKeybind {
    private static KeyBinding mainMenuInGameKeybind = new KeyBinding("key.kateclient.showMenuInGame", Keyboard.KEY_H, "key.categories.kateclient");

    private MainMenuInGameKeybind(){}

    public static KeyBinding getInstance() {
        return mainMenuInGameKeybind;
    }
}
