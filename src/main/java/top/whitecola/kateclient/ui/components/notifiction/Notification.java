package top.whitecola.kateclient.ui.components.notifiction;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import top.whitecola.animationlib.Animation;
import top.whitecola.animationlib.functions.AbstractAnimationFunction;
import top.whitecola.animationlib.functions.type.CubicOutFunction;
import top.whitecola.kateclient.ui.widget.AbstractWidget;
import top.whitecola.kateclient.utils.Render2DUtils;
import static top.whitecola.kateclient.utils.MCWrapper.*;

import java.awt.*;

public class Notification extends AbstractWidget {
    protected ResourceLocation icon;
    protected ResourceLocation background;

    protected String title = "";
    protected String content = "";
    protected Color color = new Color(31, 31, 31);
    protected Color textColor = new Color(243, 242, 241);
    protected Animation showAnimation;
    protected Animation leaveAnimation;
    protected Animation upAnimation;


    private AbstractAnimationFunction animationFunction = new CubicOutFunction();

    protected final long firstShowTime = System.currentTimeMillis();

    protected boolean shoudLeave;
    protected boolean shouldRemove;



    private Notification(float x, float y, float width, float height) {
        super(width,height);
        showAnimation = new Animation();
        showAnimation.setMin(0).setMax(100).setFunction(animationFunction).setTotalTime(600).setLock(true);

        leaveAnimation = new Animation();
        leaveAnimation.setMin(0).setMax(100).setFunction(animationFunction).setTotalTime(600).setLock(true);

        upAnimation = new Animation();


    }

    public Notification(){
        this(0,0,0,0);

    }


    @Override
    public void drawWidget() {

        if(getTotalShowedTime()>1500 && !isShoudLeave()){
            setShoudLeave(true);
        }

        ScaledResolution scaledResolution = new ScaledResolution(mc);

        float value;

        if(shoudLeave && showAnimation.isFinish()) {
            value = leaveAnimation.update();
        }else{
            value = 100 - showAnimation.update();
        }


        if(leaveAnimation.isFinish() && !isShouldRemove()){
            setShouldRemove(true);
        }

        this.relativePosition.setDefaultX(scaledResolution.getScaledWidth() - scaledResolution.getScaledWidth()/5 -7 + value +3);
        this.relativePosition.setDefaultY(scaledResolution.getScaledHeight() - scaledResolution.getScaledWidth()/14 -7);

        float x = relativePosition.getDefaultX();
        float y = relativePosition.getRelativeY();
        Render2DUtils.drawRoundedRect(x,y,x+scaledResolution.getScaledWidth()/5+6,y+scaledResolution.getScaledWidth()/14,color.getRGB(),color.getRGB());
//        Render2DUtils.drawCustomImage((int)x,(int)y,scaledResolution.getScaledWidth()/5,scaledResolution.getScaledWidth()/14,background);

        Render2DUtils.drawCustomImage((int)x+3,(int)y+3,scaledResolution.getScaledWidth()/14-6,scaledResolution.getScaledWidth()/14-6,icon);
        mc.fontRendererObj.drawStringWithShadow(title,x+scaledResolution.getScaledWidth()/14 ,y +(scaledResolution.getScaledWidth()/14/2-4),textColor.getRGB());
        super.drawWidget();
    }


    public Notification setContent(String content) {
        this.content = content;
        return this;
    }

    public Notification setIcon(ResourceLocation icon) {
        this.icon = icon;
        return this;
    }

    public Notification setTitle(String title) {
        this.title = title;
        return this;
    }

    public long getTotalShowedTime(){
        return System.currentTimeMillis() - firstShowTime;
    }


    public void setShoudLeave(boolean shoudLeave) {
        this.shoudLeave = shoudLeave;
    }

    public boolean isShoudLeave() {
        return shoudLeave;
    }

    public boolean isShouldRemove() {
        return shouldRemove;
    }

    public void setShouldRemove(boolean shouldRemove) {
        this.shouldRemove = shouldRemove;
    }
}
