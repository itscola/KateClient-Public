package top.whitecola.kateclient.ui.widget.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import top.whitecola.kateclient.ui.widget.AbstractWidget;
import top.whitecola.kateclient.utils.Render2DUtils;

import java.awt.*;

import static top.whitecola.kateclient.utils.MCWrapper.mc;

public class TextWidget extends AbstractWidget {

    public TextWidget(float x,float y,String text,Color textColor,Color backgroundColor){
        super(0,0);
        this.setText(text).setColor(textColor);
        if(backgroundColor!=null){
            setBackgroundColor(backgroundColor);
        }
    }

    @Override
    public void drawWidget() {

//        FontRenderer fontRenderer = mc.fontRendererObj;
//        int textWidth = fontRenderer.getStringWidth(text)-3;
//        if(backgroundColor!=null) {
//            Render2DUtils.drawRect(getX(), getY(), getX() + textWidth + 1, getY() + 10, backgroundColor.getRGB());
//        }
//        fontRenderer.drawStringWithShadow(getText(),getX()+1 ,getY()+1,color.getRGB());
//
        super.drawWidget();
    }


}
