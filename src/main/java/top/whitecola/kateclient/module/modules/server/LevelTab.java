package top.whitecola.kateclient.module.modules.server;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;
import top.whitecola.kateclient.utils.ServerInfoUtils;
import static top.whitecola.kateclient.utils.MCWrapper.*;

public class LevelTab extends AbstractModule {

    @Override
    public void onDisable() {
        if(KateClient.getKateClient().getModuleManager().getModuleByName("LevelHead").isEnabled()){
            KateClient.getKateClient().getModuleManager().getModuleByName("LevelHead").disable();
        }
        super.onDisable();
    }

    @Override
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        if(!(e.entity instanceof EntityPlayerSP)){
            return;
        }

        if(!ServerInfoUtils.checkHypixel()){
            mc.thePlayer.addChatComponentMessage(new ChatComponentText("[KateClient] the LevelTab module only for Hypixel server."));
            this.disable();
        }

        if(KateClient.getKateClient().getHypixelConfig().config.key==null||KateClient.getKateClient().getHypixelConfig().config.key.equals("")){
            mc.thePlayer.addChatComponentMessage(new ChatComponentText("[KateClient] Send '/api new' to get Hypixel api key for LevelTab module."));
            this.disable();
        }

        KateClient.getKateClient().hypixelAPIWrapper.getPlayerLevelCache().clear();
        super.onEntityJoinWorld(e);
    }


    @Override
    public void onLoginOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        KateClient.getKateClient().hypixelAPIWrapper.getPlayerLevelCache().clear();
        super.onLoginOut(e);
    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.SERVER;
    }

    @Override
    public String getModuleName() {
        return "LevelTab";

    }
}
