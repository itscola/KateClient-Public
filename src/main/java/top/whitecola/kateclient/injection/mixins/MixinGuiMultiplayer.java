package top.whitecola.kateclient.injection.mixins;

import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.whitecola.kateclient.ui.components.buttons.LongRectButton;
import top.whitecola.kateclient.ui.components.buttons.SwitchButton;

import java.io.IOException;

@Mixin(GuiMultiplayer.class)
public abstract class MixinGuiMultiplayer extends GuiScreen {
    @Shadow
    private GuiButton btnEditServer;
    @Shadow
    private GuiButton btnSelectServer;
    @Shadow
    private GuiButton btnDeleteServer;

    @Shadow
    private ServerSelectionList serverListSelector;

    private SwitchButton switchButton;

    /**
     * @author white_cola
     */
    @Overwrite
    public void createButtons() {
        int j = this.height / 4;

        this.buttonList.add(this.btnEditServer = new LongRectButton(7, this.width / 2 - 154, this.height - 28, 70, 20, I18n.format("selectServer.edit", new Object[0])));
        this.buttonList.add(this.btnDeleteServer = new LongRectButton(2, this.width / 2 - 74, this.height - 28, 70, 20, I18n.format("selectServer.delete", new Object[0])));
        this.buttonList.add(this.btnSelectServer = new LongRectButton(1, this.width / 2 - 154, this.height - 52, 100, 20, I18n.format("selectServer.select", new Object[0])));
        this.buttonList.add(new LongRectButton(4, this.width / 2 - 50, this.height - 52, 100, 20, I18n.format("selectServer.direct", new Object[0])));
        this.buttonList.add(new LongRectButton(3, this.width / 2 + 4 + 50, this.height - 52, 100, 20, I18n.format("selectServer.add", new Object[0])));
        this.buttonList.add(new LongRectButton(8, this.width / 2 + 4, this.height - 28, 70, 20, I18n.format("selectServer.refresh", new Object[0])));
        this.buttonList.add(new LongRectButton(0, this.width / 2 + 4 + 76, this.height - 28, 75, 20, I18n.format("gui.cancel", new Object[0])));

        this.buttonList.add(switchButton = new SwitchButton(9999,this.width / 2 + 185, j - 56 , 17,20,""));
        selectServer(this.serverListSelector.func_148193_k());
    }

    @Inject(method = "actionPerformed", at = @At(value = "HEAD"), cancellable = true)
    protected void actionPerformed(GuiButton button, CallbackInfo ci) throws IOException {
        if(button.id == 9999){
            switchButton.toggle();
        }
    }

    /**
     * @author
     */
    @Overwrite
    public void selectServer(int p_selectServer_1_) {
        this.serverListSelector.setSelectedSlotIndex(p_selectServer_1_);
        GuiListExtended.IGuiListEntry guilistextended$iguilistentry = p_selectServer_1_ < 0 ? null : this.serverListSelector.getListEntry(p_selectServer_1_);
        this.btnSelectServer.enabled = false;
        this.btnEditServer.enabled = false;
        this.btnDeleteServer.enabled = false;
        if (guilistextended$iguilistentry != null && !(guilistextended$iguilistentry instanceof ServerListEntryLanScan)) {
            this.btnSelectServer.enabled = true;
            if (guilistextended$iguilistentry instanceof ServerListEntryNormal) {
                this.btnEditServer.enabled = true;
                this.btnDeleteServer.enabled = true;
            }
        }

    }
}
