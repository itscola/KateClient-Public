package top.whitecola.kateclient.module.modules.movment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;
import top.whitecola.kateclient.ui.widget.widgets.TextWidget;
import top.whitecola.kateclient.utils.PlayerSPUtils;

import java.awt.*;

import static top.whitecola.kateclient.utils.MCWrapper.*;


public class AutoSprint extends AbstractModule {
    private Color color = new Color(40, 168, 220);
    private Color backgroundcolor = new Color(255,255,255, 32);


    public AutoSprint(){
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        widget = new TextWidget(scaledResolution.getScaledWidth()/7 ,scaledResolution.getScaledHeight()-10,this.getModuleName(),color,null);
    }

    @Override
    public void onTick() {
        if(Minecraft.getMinecraft()==null||Minecraft.getMinecraft().theWorld==null || mc.thePlayer==null){
            return;
        }

        if(!PlayerSPUtils.isMoving() || PlayerSPUtils.isSneaking() || mc.thePlayer.getFoodStats().getFoodLevel() <= 6.0F || mc.thePlayer.isPotionActive(Potion.blindness) || mc.thePlayer.isCollidedHorizontally){
            if(mc.thePlayer.isSprinting()) {
                mc.thePlayer.setSprinting(false);
            }
            return;
        }


        if(mc.thePlayer.movementInput.moveForward >= 0.8F){
            if(!mc.thePlayer.isSprinting()) {
                mc.thePlayer.setSprinting(true);
            }
            return;
        }

        super.onTick();
    }




    @Override
    public void onRenderOverLay(RenderGameOverlayEvent event) {


        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        FontRenderer fontRenderer = mc.fontRendererObj;

        fontRenderer.drawStringWithShadow("Auto Sprint",scaledResolution.getScaledWidth()/7 ,scaledResolution.getScaledHeight()-10,color.getRGB());

        super.onRenderOverLay(event);
    }


    @Override
    public void onEnable() {
//        KateClient.getKateClient().getWidgetManager().addWidget(getWidget());
        super.onEnable();
    }

    @Override
    public void onDisable() {
//        KateClient.getKateClient().getWidgetManager().removeWidget(getWidget());
        super.onDisable();
    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.MOVEMENT;
    }

    @Override
    public String getModuleName() {
        return "AutoSprint";

    }



}
