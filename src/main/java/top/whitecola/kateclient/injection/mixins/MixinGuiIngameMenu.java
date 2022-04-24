package top.whitecola.kateclient.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import top.whitecola.kateclient.ui.components.buttons.LongRectButton;

@Mixin(GuiIngameMenu.class)
public class MixinGuiIngameMenu extends GuiScreen {
    @Shadow
    private int field_146445_a;
    @Shadow
    private int field_146444_f;

    /**
     * @author white_cola
     */
    @Overwrite
    public void initGui() {
        this.field_146445_a = 0;
        this.buttonList.clear();
        int i = -16;


        this.buttonList.add(new LongRectButton(1, this.width / 2 - 100, this.height / 4 + 120 + i, I18n.format("menu.returnToMenu", new Object[0])));
        if (!this.mc.isIntegratedServerRunning()) {
            (this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
        }

        this.buttonList.add(new LongRectButton(4, this.width / 2 - 100, this.height / 4 + 24 + i, I18n.format("menu.returnToGame", new Object[0])));
        this.buttonList.add(new LongRectButton(0, this.width / 2 - 100, this.height / 4 + 96 + i, 98, 20, I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new LongRectButton(12, this.width / 2 + 2, this.height / 4 + 96 + i, 98, 20, I18n.format("fml.menu.modoptions", new Object[0])));
        GuiButton guibutton;
        this.buttonList.add(guibutton = new LongRectButton(7, this.width / 2 - 100, this.height / 4 + 72 + i, 200, 20, I18n.format("menu.shareToLan", new Object[0])));
        this.buttonList.add(new LongRectButton(5, this.width / 2 - 100, this.height / 4 + 48 + i, 98, 20, I18n.format("gui.achievements", new Object[0])));
        this.buttonList.add(new LongRectButton(6, this.width / 2 + 2, this.height / 4 + 48 + i, 98, 20, I18n.format("gui.stats", new Object[0])));
        guibutton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
    }



    /**
     * @author White_cola
     */
    @Overwrite
    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        if(Minecraft.getMinecraft().theWorld ==null){
            this.drawDefaultBackground();
        }
        this.drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40, 16777215);
        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
    }

}
