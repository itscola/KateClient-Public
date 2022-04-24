package top.whitecola.kateclient.event.events;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.event.EventAdapter;

public class EventToInvokeNotification extends EventAdapter {
    public EventToInvokeNotification() {
        super(MainMenuEvent.class.getSimpleName());
    }



    @Override
    public void onRender(TickEvent.RenderTickEvent event) {
        KateClient.getKateClient().getNotificationManager().draw();
        super.onRender(event);
    }
}
