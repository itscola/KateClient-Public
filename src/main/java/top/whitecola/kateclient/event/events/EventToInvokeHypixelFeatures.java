package top.whitecola.kateclient.event.events;

import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.event.EventAdapter;
import top.whitecola.kateclient.utils.ServerInfoUtils;

import static top.whitecola.kateclient.utils.MCWrapper.*;

public class EventToInvokeHypixelFeatures extends EventAdapter {
    private boolean isHypixel;



    public EventToInvokeHypixelFeatures() {
        super(EventToInvokeHypixelFeatures.class.getSimpleName());
    }

    @Override
    public void onLoginIn(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        isHypixel = ServerInfoUtils.checkHypixel();
        super.onLoginIn(e);
    }

    @Override
    public void onLoginOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        isHypixel = false;
        super.onLoginOut(e);
    }



    @Override
    public void onChatReceive(ClientChatReceivedEvent e) {
        if(!isHypixel){
            return;
        }

        if(isHypixel&&e.message.getFormattedText().contains("Your new API key is")){
            KateClient.getKateClient().getHypixelConfig().getConfig().key = e.message.getFormattedText().replace("Your new API key is ","").replace("§a§r§b","").replace("§r","");
            mc.thePlayer.addChatMessage(new ChatComponentText("[KateClient] your Hypixel API key is §a"+KateClient.getKateClient().getHypixelConfig().getConfig().key));
            mc.thePlayer.addChatMessage(new ChatComponentText("[KateClient] §e Now, you can use some modules for hypixel like LevelTab."));
            KateClient.getKateClient().getHypixelConfig().saveConfig();
            e.setCanceled(true);
            return;
        }

        super.onChatReceive(e);
    }



}
