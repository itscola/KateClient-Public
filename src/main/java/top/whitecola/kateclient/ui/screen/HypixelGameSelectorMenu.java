package top.whitecola.kateclient.ui.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import top.whitecola.kateclient.ui.components.buttons.IconButton;
import top.whitecola.kateclient.utils.ClientUtils;
import top.whitecola.kateclient.utils.Render2DUtils;

import java.awt.*;
import java.io.IOException;

import static top.whitecola.kateclient.utils.MCWrapper.*;

public class HypixelGameSelectorMenu extends GuiScreen {
    //I will clean up codes here when the design phrase is finished.

    protected IconButton bedwars24;
    protected IconButton bedwars44;
    protected IconButton bedwars43;
    protected IconButton bedwars81;
    protected IconButton bedwars82;
    protected Color fontColor = new Color(217, 217, 217);

    @Override
    public void initGui() {

        int range = 60;

        bedwars24 = new IconButton(0,20,20,40,40,"bedwars24");
        bedwars24.setIcon(bedwars);
        buttonList.add(bedwars24);

        bedwars44 = new IconButton(1,20 + range,20,40,40,"bedwars44");
        bedwars44.setIcon(bedwars);
        buttonList.add(bedwars44);

        bedwars43 = new IconButton(2,20 + range*2,20,40,40,"bedwars43");
        bedwars43.setIcon(bedwars);
        buttonList.add(bedwars43);

        bedwars81 = new IconButton(3,20 + range*3,20,40,40,"bedwars81");
        bedwars81.setIcon(bedwars);
        buttonList.add(bedwars81);

        bedwars82 = new IconButton(4,20 + range*4,20,40,40,"bedwars81");
        bedwars82.setIcon(bedwars);
        buttonList.add(bedwars82);


        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        if(bedwars24.isHovered(mouseX,mouseY)){
            mc.fontRendererObj.drawStringWithShadow("bedwars 4v4",0,10,fontColor.getRGB());
        }else if(bedwars44.isHovered(mouseX,mouseY)){
            mc.fontRendererObj.drawStringWithShadow("bedwars 4v4v4v4",0,10,fontColor.getRGB());
        }else if(bedwars43.isHovered(mouseX,mouseY)){
            mc.fontRendererObj.drawStringWithShadow("bedwars 3v3v3v3",0,10,fontColor.getRGB());
        }else if(bedwars81.isHovered(mouseX,mouseY)){
            mc.fontRendererObj.drawStringWithShadow("bedwars 1v1v1v1v1v1v1v1",0,10,fontColor.getRGB());
        }else if(bedwars82.isHovered(mouseX,mouseY)){
            mc.fontRendererObj.drawStringWithShadow("bedwars 2v2v2v2v2v2v2v2",0,10,fontColor.getRGB());
        }
        super.drawScreen(mouseX,mouseY,partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 0){
            mc.thePlayer.sendChatMessage("/play bedwars_two_four");
            ClientUtils.sendAClientMessage("bedwars24","",visual);
        }else if(button.id == 1){
            mc.thePlayer.sendChatMessage("/play bedwars_four_four");
            ClientUtils.sendAClientMessage("bedwars44","",visual);
        }else if(button.id == 2){
            mc.thePlayer.sendChatMessage("/play bedwars_four_three");
            ClientUtils.sendAClientMessage("bedwars43","",visual);

        }else if(button.id == 3){
            mc.thePlayer.sendChatMessage("/play /play bedwars_eight_one");
            ClientUtils.sendAClientMessage("bedwars81","",visual);

        }else if(button.id == 4) {
            mc.thePlayer.sendChatMessage("/play /play bedwars_eight_two");
            ClientUtils.sendAClientMessage("bedwars82", "", visual);
        }


        super.actionPerformed(button);
    }
}
