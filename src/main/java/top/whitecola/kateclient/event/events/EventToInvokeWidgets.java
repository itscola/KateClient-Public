package top.whitecola.kateclient.event.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.event.EventAdapter;

import static top.whitecola.kateclient.utils.MCWrapper.mc;

public class EventToInvokeWidgets extends EventAdapter {
    public EventToInvokeWidgets() {
        super(EventToInvokeWidgets.class.getSimpleName());
    }

    @Override
    public void onRenderOverLay(RenderGameOverlayEvent event) {
        if(Minecraft.getMinecraft()==null||Minecraft.getMinecraft().theWorld==null || mc.thePlayer==null){
            return;
        }

        if (event.type != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        KateClient.getKateClient().getWidgetManager().drawWidgets();
        super.onRenderOverLay(event);
    }
}
