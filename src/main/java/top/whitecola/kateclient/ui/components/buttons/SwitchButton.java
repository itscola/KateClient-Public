package top.whitecola.kateclient.ui.components.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import top.whitecola.kateclient.ui.UICache;
import top.whitecola.kateclient.utils.Render2DUtils;

import java.awt.*;


public class SwitchButton extends AbstractButton{
    protected Color color = new Color(31, 31, 31);
    protected Color firstCircleColor = new Color(250, 250, 250);
    protected Color finalCircleColor = new Color(61, 132,254);
    protected Color presentCircleColor = firstCircleColor;

    boolean switched;
    double switchPosx = 1;
    double firstPosx = 1;
    double finalPosx = 17;


    public SwitchButton(int buttonId, int x, int y, int width, int height, String buttonText) {
        super(buttonId, x, y, width, height, buttonText);
    }

    public SwitchButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(switched){
            switchPosx +=2.5;
            if(switchPosx > finalPosx){
                switchPosx = finalPosx;
                presentCircleColor = finalCircleColor;
            }

        }else{
            switchPosx -=2.5;
            if(switchPosx < firstPosx){
                switchPosx = firstPosx;
                presentCircleColor = firstCircleColor;
            }


        }

        Render2DUtils.drawCircleRect((float) this.xPosition, (float)this.yPosition, (float)this.xPosition + this.width, (float)this.yPosition + this.height,4,color.getRGB());;
        Render2DUtils.drawCircle(this.xPosition,this.yPosition + (this.height / 2),this.height / 2.107, color.getRGB());
        Render2DUtils.drawCircle(this.xPosition + this.width,this.yPosition + (this.height / 2),this.height / 2.107, color.getRGB());

        Render2DUtils.drawCircle(this.xPosition + switchPosx ,this.yPosition + (this.height / 2),this.height / 2.6, presentCircleColor.getRGB());

        super.drawButton(mc, mouseX, mouseY);
    }

    @Override
    public void playPressSound(SoundHandler p_playPressSound_1_) {
        super.playPressSound(p_playPressSound_1_);
    }

    public void toggle(){
        if(!switched){
            switched = true;
        }else {
            switched = false;
        }

        UICache.mainUISwitched = switched;


    }

    public void forceToggle(boolean toggle){
        this.switched = toggle;
        if(switched){
            switchPosx = finalPosx;
        }else{
            switchPosx = firstPosx;
        }
    }


}
