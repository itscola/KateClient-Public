package top.whitecola.kateclient.injection.mixins;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.dedicated.DedicatedPlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(net.minecraft.client.gui.Gui.class)
public class MixinGUI {
    public Color color = new Color(146,165,178);

    @Inject(method = "drawCenteredString", at = @At(value = "HEAD"), cancellable = true)
    public void drawCenteredString(FontRenderer p_drawCenteredString_1_, String p_drawCenteredString_2_, int p_drawCenteredString_3_, int p_drawCenteredString_4_, int p_drawCenteredString_5_, CallbackInfo ci){
        if(p_drawCenteredString_2_.equals("KateClient")){
            p_drawCenteredString_1_.drawStringWithShadow(p_drawCenteredString_2_, (float)(p_drawCenteredString_3_ - p_drawCenteredString_1_.getStringWidth(p_drawCenteredString_2_) / 2), (float)p_drawCenteredString_4_, color.getRGB());

            ci.cancel();
            return;

        }

    }

}
