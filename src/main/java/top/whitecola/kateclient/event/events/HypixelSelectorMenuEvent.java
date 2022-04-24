package top.whitecola.kateclient.event.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.event.EventAdapter;
import top.whitecola.kateclient.keybinds.HypixelGameSelectorKeybind;
import top.whitecola.kateclient.keybinds.MainMenuInGameKeybind;
import top.whitecola.kateclient.ui.screen.HypixelGameSelectorMenu;
import top.whitecola.kateclient.ui.screen.MainClickUIIngame;

public class HypixelSelectorMenuEvent extends EventAdapter {

    public HypixelSelectorMenuEvent() {
        super(HypixelSelectorMenuEvent.class.getSimpleName());

    }

    @Override
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        if(KateClient.getKateClient().getModuleManager().getModuleByName("HypixelSelector")==null){
            return;
        }

        if(!KateClient.getKateClient().getModuleManager().getModuleByName("HypixelSelector").isEnabled()){
            return;
        }

        if(HypixelGameSelectorKeybind.getInstance().isPressed()){
            Minecraft.getMinecraft().displayGuiScreen(new HypixelGameSelectorMenu());
        }


    }

}
