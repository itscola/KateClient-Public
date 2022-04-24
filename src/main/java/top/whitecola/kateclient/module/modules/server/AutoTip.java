package top.whitecola.kateclient.module.modules.server;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;
import top.whitecola.kateclient.utils.ClientUtils;
import static top.whitecola.kateclient.utils.ServerInfoUtils.*;

import static top.whitecola.kateclient.utils.MCWrapper.*;

public class AutoTip extends AbstractModule {
    protected long lastTip;

    @Override
    public void onEnable() {
        if(checkHypixel() && needTip()){
            tip();
        }

        super.onEnable();
    }

    @Override
    public void onLoginIn(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        if(checkHypixel()){
            ClientUtils.sendAClientMessage("Hypixel","",world);
        }

        super.onLoginIn(e);
    }

    @Override
    public void onLoginOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        super.onLoginOut(e);
    }

    @Override
    public void onChatReceive(ClientChatReceivedEvent e) {
        if(checkHypixel()&&needTip()){
            tip();
        }
        super.onChatReceive(e);
    }

    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.SERVER;
    }

    @Override
    public String getModuleName() {
        return "AutoTip";

    }



    public void setLastTip(long lastTip) {
        this.lastTip = lastTip;
    }

    public long getLastTip() {
        return lastTip;
    }

    public void tip(){
        if(mc.theWorld==null || mc.thePlayer==null){
            return;
        }

        ClientUtils.sendAClientMessage("/tipall","");
        mc.thePlayer.sendChatMessage("/tipall");
        this.setLastTip(System.currentTimeMillis());
    }

    public boolean needTip(){
        if(System.currentTimeMillis()-getLastTip()>=300000 || getLastTip()==0){
            return true;
        }

        return false;
    }


}
