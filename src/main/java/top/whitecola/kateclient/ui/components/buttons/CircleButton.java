package top.whitecola.kateclient.ui.components.buttons;

import javafx.scene.shape.Circle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import org.lwjgl.input.Mouse;
import top.whitecola.kateclient.utils.GUIUtils;
import top.whitecola.kateclient.utils.Render2DUtils;

import java.awt.*;

public class CircleButton extends AbstractButton{
    protected Color color = new Color(255, 255, 255);
    protected float dragX;
    protected float dragY;

    protected boolean draged;


    public CircleButton(int buttonId, int x, int y, int width, int height, String buttonText) {
        super(buttonId, x, y, width, height, buttonText);
    }

    public CircleButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(GUIUtils.isHovered(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height,mouseX,mouseY) && Mouse.isButtonDown(0)){
            if (dragX == 0 && dragY == 0) {
                dragX = mouseX - xPosition;
                dragY = mouseY - yPosition;
            } else {
                xPosition = (int)(mouseX - dragX);
                yPosition = (int)(mouseY - dragY);
            }
            draged = true;
        } else if (dragX != 0 || dragY != 0) {
            dragX = 0;
            dragY = 0;
        }

        Render2DUtils.drawCircle(this.xPosition +6 ,this.yPosition +6,this.height / 2.4, color.getRGB());


    }


    @Override
    public void playPressSound(SoundHandler p_playPressSound_1_) {
//        super.playPressSound(p_playPressSound_1_);
    }

    public CircleButton setColor(Color color) {
        this.color = color;
        return this;
    }
}
