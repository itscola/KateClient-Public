package top.whitecola.kateclient.event.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import top.whitecola.kateclient.event.EventAdapter;
import top.whitecola.kateclient.keybinds.MainMenuInGameKeybind;
import top.whitecola.kateclient.ui.screen.HypixelGameSelectorMenu;
import top.whitecola.kateclient.ui.screen.MainClickUIIngame;

public class MainMenuEvent extends EventAdapter {

    public MainMenuEvent() {
        super(MainMenuEvent.class.getSimpleName());

    }

    @Override
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        if(MainMenuInGameKeybind.getInstance().isPressed()){
            Minecraft.getMinecraft().displayGuiScreen(new MainClickUIIngame());
        }


    }
}
