package top.whitecola.kateclient.injection.mixins;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.INetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.event.EventManager;
import top.whitecola.kateclient.ui.widget.WidgetManager;
import static top.whitecola.kateclient.utils.MCWrapper.*;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame {
    @Shadow protected abstract void renderPumpkinOverlay(ScaledResolution p_renderPumpkinOverlay_1_);

    protected WidgetManager widgetManager;
    {
        widgetManager = KateClient.getKateClient().getGameUI().widgetManager;
    }

    @Inject(method = "renderGameOverlay", at = @At(value = "HEAD"), cancellable = true)
    public void renderGameOverlay(float p_renderGameOverlay_1_, CallbackInfo ci){
        EventManager.getEventManager().renderGameOverlayRETURN();
    }



}
