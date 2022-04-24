package top.whitecola.kateclient.ui.widget.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import top.whitecola.kateclient.ui.widget.AbstractWidget;
import top.whitecola.kateclient.utils.Render2DUtils;

import java.awt.*;

import static top.whitecola.kateclient.utils.MCWrapper.mc;

public class FPSWidget extends AbstractWidget {


    public FPSWidget(float x,float y,float width,float height){
        super(width,height);
        setColor(new Color(239, 239, 239));
        setBackgroundColor(new Color(255,255,255, 32));
    }

    @Override
    public void drawWidget() {

        FontRenderer fontRenderer = mc.fontRendererObj;
        String text = "[FPS] "+Minecraft.getDebugFPS();
//        int textWidth = fontRenderer.getStringWidth(text)-3;
//        Render2DUtils.drawRect(getX(),getY(),getX()+textWidth+1,getY()+10,getBackgroundColor().getRGB());
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.9F, 0.9F, 0.9F);
        fontRenderer.drawStringWithShadow(text,relativePosition.getRelativeX()+1 ,relativePosition.getRelativeY()+1,getColor().getRGB());
        GlStateManager.popMatrix();
        super.drawWidget();
    }



}
