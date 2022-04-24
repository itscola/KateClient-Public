package top.whitecola.kateclient.ui.screen;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.ui.UICache;
import top.whitecola.kateclient.ui.widget.AbstractWidget;
import top.whitecola.kateclient.ui.widget.WidgetManager;

import java.util.Vector;

public class WidgetEditorScreen extends GuiScreen {
    protected ResourceLocation backgroud;
    protected Vector<AbstractWidget> widgets;
    protected WidgetManager widgetManager;

    @Override
    public void initGui() {
        widgetManager = KateClient.getKateClient().getWidgetManager();
        widgets = widgetManager.getWidgets();
        drawDefaultBackground();
        super.initGui();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {


//        for(AbstractWidget widget : widgets){
//
//            widget.drawWidget();
//            if(widget.isHovered(mouseX,mouseY) && Mouse.isButtonDown(0)){
//                if (widget.dragX == 0 && widget.dragY == 0) {
//                    widget.dragX = mouseX - widget.getX();
//                    widget.dragY = mouseY - widget.getY();
//                } else {
//                    widget.setX(mouseX - widget.dragX);
//                    widget.setY(mouseY - widget.dragY);
//                }
//                widget.draged = true;
//            }else if(widget.dragX != 0 || widget.dragY != 0){
//
//                widget.dragX = 0;
//                widget.dragY = 0;
//                if(widget.draged){
//                    widget.draged = false;
//                }
//            }
//
//
//        }




        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
