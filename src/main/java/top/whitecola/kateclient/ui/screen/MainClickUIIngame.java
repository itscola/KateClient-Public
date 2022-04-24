package top.whitecola.kateclient.ui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import top.whitecola.animationlib.Animation;
import top.whitecola.animationlib.functions.type.*;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;
import top.whitecola.kateclient.ui.UICache;
import top.whitecola.kateclient.ui.components.buttons.CircleButton;
import top.whitecola.kateclient.ui.components.buttons.IconButton;
import top.whitecola.kateclient.ui.screen.innerscreen.ClickGUIEntry;
import top.whitecola.kateclient.utils.ClientUtils;
import top.whitecola.kateclient.utils.GUIUtils;
import top.whitecola.kateclient.utils.Render2DUtils;
import static top.whitecola.kateclient.utils.MCWrapper.*;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class MainClickUIIngame extends GuiScreen {
    protected float width = 300;
    protected float height = 200;
    protected float xPosition = 90;
    protected float yPosition = 16;

    protected float dragX;
    protected float dragY;

    protected boolean draged;

    private boolean needClose;
    private boolean closed;


    protected Color mainColor = new Color(33, 33, 33);
    protected Color mainBarColor = new Color(28, 28, 28);
    protected Color mainBarDragColor = new Color(18, 80, 123);


    protected Color circleButtonColor1 = new Color(252, 98, 93);
    protected Color circleButtonColor2 = new Color(253, 188, 64);
    protected Color circleButtonColor3 = new Color(53, 205, 75);

    protected Color moduleButtonColor = new Color(23, 23, 23);
    protected Color moduleButtonColor2 = new Color(26, 26, 26);

//    protected Color dockColor = new Color(245,245,245, 200);

    protected Animation displayAnimation = new Animation();
    protected Animation closeAnimation = new Animation();



    protected Color mainTextColor = new Color(196, 210, 210);
    protected Color fontColor = new Color(220, 220, 218);
    protected Color enabledfontColor = new Color(98, 168, 229);



    protected CircleButton circleButton;
    protected CircleButton circleButton2;
    protected CircleButton circleButton3;

    protected IconButton settingButton;
    protected IconButton messageButton;


    protected ResourceLocation sprint = new ResourceLocation("kateclient","ui/components/sprint.png");
    protected ResourceLocation threepoints = new ResourceLocation("kateclient","ui/components/threepoints.png");
    protected ResourceLocation switchon = new ResourceLocation("kateclient","ui/components/switchon.png");

    protected Vector<ClickGUIEntry> entries = new Vector<ClickGUIEntry>();
    int rollingValue = 0;





    public MainClickUIIngame(){
        initAnimation();
    }

    public void initAnimation(){
        displayAnimation.setMin(0).setMax(150).setFunction(new CubicOutFunction()).setTotalTime(300);
        closeAnimation.setMin(0).setMax(200).setFunction(new CubicOutFunction()).setTotalTime(150);
    }

    @Override
    public void initGui() {
        super.initGui();
        KateClient.getKateClient().getModuleConfig().config.loadConfigForModules();

        yPosition = UICache.mainUIPosY;
        xPosition = UICache.mainUIPosX;

        buttonList.clear();
        circleButton = new CircleButton(0,(int)this.xPosition+6,(int)this.yPosition+6,(int)this.width,(int)this.height / 30,"");
        circleButton2 = new CircleButton(1,(int)this.xPosition+6,(int)this.yPosition+6,(int)this.width,(int)this.height / 30,"");
        circleButton3 = new CircleButton(2,(int)this.xPosition+6,(int)this.yPosition+6,(int)this.width,(int)this.height / 30,"");
        settingButton = new IconButton(3,(int)this.xPosition +6, (int)this.yPosition +(int)this.height -32,25,25,"").setIcon(setting);
        messageButton = new IconButton(4,(int)this.xPosition +14 +25, (int)this.yPosition +(int)this.height -32,25,25,"").setIcon(message);

        this.buttonList.add(circleButton.setColor(circleButtonColor1));
        this.buttonList.add(circleButton2.setColor(circleButtonColor2));
        this.buttonList.add(circleButton3.setColor(circleButtonColor3));
        this.buttonList.add(settingButton);
        this.buttonList.add(messageButton);


        loadDefaultEntries();

        ClientUtils.sendAClientMessage("ClickGUI ON","");



    }


    public void loadDefaultEntries(){
        clearEntries();

        //some handler later
        Vector<AbstractModule> modules = KateClient.getKateClient().getModuleManager().getModules();
        for(AbstractModule module: modules){
            addEntrie(new ClickGUIEntry().fromModule(module));
        }

    }

    public void loadEntriesByCategory(ModuleCategory category){
        clearEntries();
        //some handler later
        Vector<AbstractModule> modules = KateClient.getKateClient().getModuleManager().getModules();
        for(AbstractModule module: modules){
            if(module.getModuleType().equals(category)) {
                addEntrie(new ClickGUIEntry().fromModule(module));
            }
        }
    }


    public void addEntrie(ClickGUIEntry entry){
        this.entries.add(entry);
    }

    public void removeEntrie(ClickGUIEntry entry){
        this.entries.remove(entry);
    }

    public void clearEntries(){
        this.entries.clear();
    }




//    public static MainClickUIIngame getGui() {
//        return gui;
//    }



    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //todo: I will clean up the codes here later.

        float value;

        if(isNeedClose() && displayAnimation.isFinish()) {

            value = 150 - closeAnimation.update();
            if(closeAnimation.isFinish()){
                super.onGuiClosed();
                setClosed(true);
                Minecraft.getMinecraft().displayGuiScreen(null);
                return;
            }

        }else {
            value = displayAnimation.update();
        }



        height = 50 + value;

        if(GUIUtils.isHovered(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + 16,mouseX,mouseY) && Mouse.isButtonDown(0)){

            if (dragX == 0 && dragY == 0) {
                dragX = mouseX - xPosition;
                dragY = mouseY - yPosition;
            } else {
                xPosition = mouseX - dragX;
                yPosition = mouseY - dragY;
            }
            draged = true;

        } else if (dragX != 0 || dragY != 0) {
            dragX = 0;
            dragY = 0;
            if(draged){
                UICache.mainUIPosX = xPosition;
                UICache.mainUIPosY = yPosition;
                draged = false;
            }
        }











        // draw main gui background
        circleButton.xPosition = (int)xPosition;
        circleButton.yPosition = (int)yPosition;

        circleButton2.xPosition = (int)xPosition +9;
        circleButton2.yPosition = (int)yPosition;

        circleButton3.xPosition = (int)xPosition + 18;
        circleButton3.yPosition = (int)yPosition;

        settingButton.xPosition = (int)this.xPosition +6;
        settingButton.yPosition = (int)this.yPosition +(int)this.height -32;

        messageButton.xPosition = (int)this.xPosition +14 +25;
        messageButton.yPosition = (int)this.yPosition +(int)this.height -32;



        Render2DUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, this.mainColor.getRGB(),this.mainColor.getRGB());



        if(!isNeedClose()){


//            if(displayAnimation.isFinish()) {




            for(int i=0;i<this.entries.size();i++){
                float yRange = this.height/7 * i;
                float firstY = this.yPosition + 16 +rollingValue +yRange;
                float lastY = this.yPosition + this.height / 5 +rollingValue +yRange;

                if(firstY<yPosition || lastY>yPosition+height){
                    continue;
                }

                ClickGUIEntry entry = entries.get(i);

//                if(this.yPosition>=this.yPosition + 16 +rollingValue +yRange || this.yPosition + this.height / 5 +rollingValue +yRange<=this.yPosition+height){
//                    continue;
//                }

                entry.setPos(this.xPosition + 3, firstY, this.xPosition + this.width  - 3, lastY);
                Render2DUtils.drawRoundedRect(this.xPosition + 3, firstY, this.xPosition + this.width  - 3, lastY, moduleButtonColor.getRGB(), moduleButtonColor.getRGB());
                Render2DUtils.drawRoundedRect(this.xPosition + this.height/8 +8, firstY, this.xPosition + this.width  - 3, lastY, moduleButtonColor2.getRGB(), moduleButtonColor2.getRGB());
                ResourceLocation cube;
                Color font;

                if(entry.isEnabled()) {
                    cube = cube1;
                    font = this.enabledfontColor;
                }else{
                    cube = cube2;
                    font = this.fontColor;
                }
                Render2DUtils.drawCustomImage((int) (this.xPosition + this.height / 25), (int) this.yPosition + 16 + 2 + rollingValue + (int) yRange, (int) this.width / 15, (int) this.width / 15, cube);
                fontRendererObj.drawStringWithShadow(entry.getEntryName(),this.xPosition + this.height/5.5f,this.yPosition + 24 +rollingValue +yRange,font.getRGB());
                Render2DUtils.drawCustomImage((int)(this.xPosition + this.height + 78), (int)this.yPosition + 20 +rollingValue +(int)yRange, (int)this.width /18, (int)this.width /18,threepoints);
                Render2DUtils.drawCustomImage((int)(this.xPosition + this.height + 55), (int)this.yPosition + 20 +rollingValue +(int)yRange, (int)this.width /18, (int)this.width /18,switchon);





            }

            if(GUIUtils.isHovered(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition +this.height,mouseX,mouseY)){
                int dwheel = Mouse.getDWheel();

                if(dwheel<0 && rollingValue>=-(entries.size()-(entries.size()*0.2))*(this.height/5 - 16)){

                    rollingValue -=15;

                    //down

                }else if(dwheel>0 && rollingValue<=-15){
                    rollingValue +=15;
                    //up
                }
            }


            // Just for design , I will make them into button later.

//            Render2DUtils.drawRoundedRect(this.xPosition + 3, this.yPosition + 16 +yRange +rollingValue, this.xPosition + this.width  - 3, this.yPosition + this.height / 5 +yRange +rollingValue, moduleButtonColor.getRGB(), moduleButtonColor.getRGB());
//            Render2DUtils.drawRoundedRect(this.xPosition + this.height/8 +8, this.yPosition + 16 +yRange +rollingValue, this.xPosition + this.width  - 3, this.yPosition + this.height / 5 +yRange +rollingValue, moduleButtonColor2.getRGB(), moduleButtonColor2.getRGB());
//            Render2DUtils.drawCustomImage((int)(this.xPosition + this.height/25), (int)(this.yPosition + 16+2+yRange) +rollingValue, (int)this.width /15, (int)this.width /15,cube);
//            fontRendererObj.drawStringWithShadow("DisplayPing  -  display pings ...",this.xPosition + this.height/5.5f,this.yPosition + 24 +yRange +rollingValue,fontColor.getRGB());
//            Render2DUtils.drawCustomImage((int)(this.xPosition + this.height + 78), (int)(this.yPosition + 20+yRange) +rollingValue, (int)this.width /18, (int)this.width /18,threepoints);
//            Render2DUtils.drawCustomImage((int)(this.xPosition + this.height + 55), (int)(this.yPosition + 20+yRange) +rollingValue, (int)this.width /18, (int)this.width /18,switchon);


//            }


            Render2DUtils.drawRoundedRect(this.xPosition, this.yPosition +height-42, this.xPosition + this.width, this.yPosition + height, this.mainColor.getRGB(),this.mainColor.getRGB());


            // Just for design , I will make them into button later.
            Render2DUtils.drawCustomImage((int)this.xPosition +(14 +25 ) +25 +8, (int)this.yPosition +(int)this.height -32,25,25,world);
            Render2DUtils.drawCustomImage((int)this.xPosition +(14 +25 ) +25 +8 +25 + 8, (int)this.yPosition +(int)this.height -32,25,25,visual);
            Render2DUtils.drawCustomImage((int)this.xPosition +(14 +25 ) +25 +8 +25 + 8 + 25 + 8, (int)this.yPosition +(int)this.height -32,25,25,sound);
            Render2DUtils.drawCustomImage((int)this.xPosition +(14 +25 ) +25 +8 +25 + 8 + 25 + 8 + 25 + 8, (int)this.yPosition +(int)this.height -32,25,25,render);
            Render2DUtils.drawCustomImage((int)this.xPosition +(14 +25 ) +25 +8 +25 + 8 + 25 + 8 + 25 + 8 + 25 + 8, (int)this.yPosition +(int)this.height -32,25,25,movement);
            Render2DUtils.drawCustomImage((int)this.xPosition +(14 +25 ) +25 +8 +25 + 8 + 25 + 8 + 25 + 8 + 25 + 8 + 25 + 8, (int)this.yPosition +(int)this.height -32,25,25,server);
            Render2DUtils.drawCustomImage((int)this.xPosition +(14 +25 ) +25 +8 +25 + 8 + 25 + 8 + 25 + 8 + 25 + 8 + 25 + 8 + 25 + 8, (int)this.yPosition +(int)this.height -32,25,25,mods);


        }


        if(draged){
            Render2DUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + 12, this.mainBarDragColor.getRGB(),this.mainBarDragColor.getRGB());
        }else {
            Render2DUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + 12, this.mainBarColor.getRGB(),this.mainBarColor.getRGB());
        }
//        Render2DUtils.drawRoundedRect(this.xPosition +6, this.yPosition +this.height -28, this.xPosition + this.width-6, this.yPosition + this.height - 6, this.dockColor.getRGB(),this.dockColor.getRGB());




        FontRenderer fontRenderer = mc.fontRendererObj;
        fontRenderer.drawString(KateClient.MODID, (int)(this.xPosition +32 ), (int) this.yPosition +2, this.mainTextColor.getRGB());;


        super.drawScreen(mouseX,mouseY,partialTicks);
    }



    @Override
    public void onGuiClosed() {
        ClientUtils.sendAClientMessage("ClickGUI OFF","");
        KateClient.getKateClient().getModuleConfig().config.modulesToConfig();
        KateClient.getKateClient().getModuleConfig().saveConfig();
        closeGUI();
    }



    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for(ClickGUIEntry entry : entries){
            if(GUIUtils.isHovered(entry.getxPosition(),entry.getyPosition(),entry.getX2Position(),entry.getY2Position(),mouseX,mouseY)){
                entry.toggle();
                playButtonSound();
            }
        }


        super.mouseClicked(mouseX, mouseY, mouseButton);

    }

    @Override
    protected void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {
        super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
//        if(button.id == 0){
////            Minecraft.getMinecraft().displayGuiScreen(null);
//            closeGUI();
//            playButtonSound();
//        }
    }

    private void playButtonSound(){
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }


    public void closeGUI(){
        setNeedClose(true);
    }


    public boolean isNeedClose() {
        return needClose;
    }

    public void setNeedClose(boolean needClose) {
        removeButtons();
        this.needClose = needClose;
    }


    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void removeButtons(){
        this.buttonList.remove(messageButton);
        this.buttonList.remove(settingButton);


    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
//        this.scrollList.handleMouseInput();
    }
}
