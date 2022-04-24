package top.whitecola.kateclient.injection.mixins;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.whitecola.kateclient.ui.UICache;
import top.whitecola.kateclient.ui.components.buttons.LongRectButton;
import top.whitecola.kateclient.ui.components.buttons.SwitchButton;
import top.whitecola.kateclient.utils.ClientUtils;
import top.whitecola.kateclient.utils.Render2DUtils;
import static top.whitecola.kateclient.utils.MCWrapper.*;

import java.io.IOException;

@Mixin(GuiMainMenu.class)
public class MixinGUIMainMenu extends GuiScreen {
    @Shadow
    private DynamicTexture viewportTexture;
    @Shadow
    private ResourceLocation backgroundTexture;

    @Shadow private String splashText;

    @Shadow
    private float updateCounter;

    @Shadow
    private String openGLWarning1;
    @Shadow
    private String openGLWarning2;
    @Shadow
    private String openGLWarningLink;

    private boolean welcomed;

//    @Shadow
//    private int field_92024_r;
//    @Shadow
//    private int field_92023_s;
//    @Shadow
//    private int field_92022_t;
//    @Shadow
//    private int field_92021_u;
//    @Shadow
//    private int field_92020_v;
//    @Shadow
//    private int field_92019_w;
//
//    @Shadow
//    private GuiScreen field_183503_M;
//
//    @Shadow
//    private static final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");



    protected ResourceLocation background = new ResourceLocation("kateclient","pic/background2.jpg");
    protected SwitchButton switchButton;




//    protected ResourceLocation minecraftLogo = new ResourceLocation("kateclient","minecraft.png");


    /**
     * @author white_cola
     */
    @Overwrite
    public void initGui() {


        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        this.splashText = "";


        int j = this.height / 4 + 48;

        switchButton = new SwitchButton(9999,this.width / 2 + 2 +150, j + 72 + 12, 17,20,"");
        switchButton.forceToggle(UICache.mainUISwitched);
        this.buttonList.add(switchButton);

        this.buttonList.add(new LongRectButton(1, this.width / 2 - 100, j, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new LongRectButton(2, this.width / 2 - 100, j + 24 * 1, I18n.format("menu.multiplayer", new Object[0])));

        this.buttonList.add(new LongRectButton(14, this.width / 2 + 2, j + 24 * 2, 98, 20, I18n.format("menu.online", new Object[0]).replace("Minecraft", "").trim()));
        this.buttonList.add(new LongRectButton(0, this.width / 2 - 100 -3, j + 72 + 12, 98, 20, I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new LongRectButton(4, this.width / 2 + 2 +3, j + 72 + 12, 98, 20, I18n.format("menu.quit", new Object[0])));
        this.buttonList.add(new LongRectButton(6, this.width / 2 - 100, j + 24 * 2, 98, 20, I18n.format("fml.menu.mods", new Object[0])));

    }

    @Inject(method = "actionPerformed", at = @At(value = "HEAD"), cancellable = true)
    protected void actionPerformed(GuiButton button, CallbackInfo ci) throws IOException {
        if(button.id == 9999){
            switchButton.toggle();
        }
    }

    /**
     * @author White_cola
     * @reason Change the background.
     */
    @Overwrite
    private void renderSkybox(int p_renderSkybox_1_, int p_renderSkybox_2_, float p_renderSkybox_3_) {
        Render2DUtils.drawCustomImage(0,0,width,height,background);
        if(!isWelcomed()){
            setWelcomed(true);
            ClientUtils.sendAClientMessage("KateClient","",visual);
        }
    }

    public void setWelcomed(boolean welcomed) {
        this.welcomed = welcomed;
    }

    public boolean isWelcomed() {
        return welcomed;
    }
}
