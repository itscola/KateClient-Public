package top.whitecola.kateclient.module.modules.server;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;
import top.whitecola.kateclient.utils.ServerInfoUtils;

import static top.whitecola.kateclient.utils.MCWrapper.mc;

public class LevelHead extends AbstractModule {


    @Override
    public void onEnable() {
        if(!KateClient.getKateClient().getModuleManager().getModuleByName("LevelTab").isEnabled()){
            KateClient.getKateClient().getModuleManager().getModuleByName("LevelTab").enable();
        }
        super.onEnable();
    }

    @Override
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        if(!(e.entity instanceof EntityPlayerSP)){
            return;
        }

        if(!ServerInfoUtils.checkHypixel()){
            mc.thePlayer.addChatComponentMessage(new ChatComponentText("[KateClient] the LevelHead module only for Hypixel server."));
            this.disable();
        }
        super.onEntityJoinWorld(e);
    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.SERVER;
    }

    @Override
    public String getModuleName() {
        return "LevelHead";

    }
}
