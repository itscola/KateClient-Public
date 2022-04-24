package top.whitecola.kateclient.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import top.whitecola.kateclient.ui.components.buttons.LongRectButton;

@Mixin(GuiOptions.class)
public class MixinGuiOptions extends GuiScreen{

    @Shadow
    protected String field_146442_a = "Options";
    @Shadow
    private static final GameSettings.Options[] field_146440_f;

    @Shadow
    private GuiButton field_175357_i;
    @Shadow
    private GuiLockIconButton field_175356_r;

    @Shadow
    private GameSettings game_settings_1;

    @Shadow
    private GuiScreen field_146441_g;


    /**
     * @author white_cola
     */
    @Overwrite
    public void initGui() {
        int lvt_1_1_ = 0;
        this.field_146442_a = I18n.format("options.title", new Object[0]);
        GameSettings.Options[] lvt_2_1_ = field_146440_f;
        int lvt_3_1_ = lvt_2_1_.length;

        for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
            GameSettings.Options lvt_5_1_ = lvt_2_1_[lvt_4_1_];
            if (lvt_5_1_.getEnumFloat()) {
                this.buttonList.add(new GuiOptionSlider(lvt_5_1_.returnEnumOrdinal(), this.width / 2 - 155 + lvt_1_1_ % 2 * 160, this.height / 6 - 12 + 24 * (lvt_1_1_ >> 1), lvt_5_1_));
            } else {
                GuiOptionButton lvt_6_1_ = new GuiOptionButton(lvt_5_1_.returnEnumOrdinal(), this.width / 2 - 155 + lvt_1_1_ % 2 * 160, this.height / 6 - 12 + 24 * (lvt_1_1_ >> 1), lvt_5_1_, this.game_settings_1.getKeyBinding(lvt_5_1_));
                this.buttonList.add(lvt_6_1_);
            }

            ++lvt_1_1_;
        }

        if (this.mc.theWorld != null) {
            EnumDifficulty lvt_2_2_ = this.mc.theWorld.getDifficulty();
            this.field_175357_i = new GuiButton(108, this.width / 2 - 155 + lvt_1_1_ % 2 * 160, this.height / 6 - 12 + 24 * (lvt_1_1_ >> 1), 150, 20, this.func_175355_a(lvt_2_2_));
            this.buttonList.add(this.field_175357_i);
            if (this.mc.isSingleplayer() && !this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
                this.field_175357_i.setWidth(this.field_175357_i.getButtonWidth() - 20);
                this.field_175356_r = new GuiLockIconButton(109, this.field_175357_i.xPosition + this.field_175357_i.getButtonWidth(), this.field_175357_i.yPosition);
                this.buttonList.add(this.field_175356_r);
                this.field_175356_r.func_175229_b(this.mc.theWorld.getWorldInfo().isDifficultyLocked());
                this.field_175356_r.enabled = !this.field_175356_r.func_175230_c();
                this.field_175357_i.enabled = !this.field_175356_r.func_175230_c();
            } else {
                this.field_175357_i.enabled = false;
            }
        } else {
            GuiOptionButton lvt_2_3_ = new GuiOptionButton(GameSettings.Options.REALMS_NOTIFICATIONS.returnEnumOrdinal(), this.width / 2 - 155 + lvt_1_1_ % 2 * 160, this.height / 6 - 12 + 24 * (lvt_1_1_ >> 1), GameSettings.Options.REALMS_NOTIFICATIONS, this.game_settings_1.getKeyBinding(GameSettings.Options.REALMS_NOTIFICATIONS));
            this.buttonList.add(lvt_2_3_);
        }

        this.buttonList.add(new LongRectButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("options.skinCustomisation", new Object[0])));
        this.buttonList.add(new LongRectButton(8675309, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, "Super Secret Settings..."));
        this.buttonList.add(new LongRectButton(106, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.sounds", new Object[0])));
        this.buttonList.add(new LongRectButton(107, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.stream", new Object[0])));
        this.buttonList.add(new LongRectButton(101, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.video", new Object[0])));
        this.buttonList.add(new LongRectButton(100, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.controls", new Object[0])));
        this.buttonList.add(new LongRectButton(102, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.language", new Object[0])));
        this.buttonList.add(new LongRectButton(103, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.chat.title", new Object[0])));
        this.buttonList.add(new LongRectButton(105, this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, I18n.format("options.resourcepack", new Object[0])));
        this.buttonList.add(new LongRectButton(104, this.width / 2 + 5, this.height / 6 + 144 - 6, 150, 20, I18n.format("options.snooper.view", new Object[0])));
        this.buttonList.add(new LongRectButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

//    @Inject(method = "<init>", at = @At(value = "HEAD"), cancellable = true)
//    public void GuiOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_, CallbackInfo ci){
//        this.field_146441_g = p_i1046_1_;
//        this.game_settings_1 = p_i1046_2_;
//        ci.cancel();
//    }


    /**
     * @author white_cola
     */
    @Overwrite
    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        if(Minecraft.getMinecraft().theWorld ==null){
            this.drawDefaultBackground();
        }
        this.drawCenteredString(this.fontRendererObj, this.field_146442_a, this.width / 2, 15, 16777215);
        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
    }

    public String func_175355_a(EnumDifficulty p_175355_1_) {
        IChatComponent lvt_2_1_ = new ChatComponentText("");
        lvt_2_1_.appendSibling(new ChatComponentTranslation("options.difficulty", new Object[0]));
        lvt_2_1_.appendText(": ");
        lvt_2_1_.appendSibling(new ChatComponentTranslation(p_175355_1_.getDifficultyResourceKey(), new Object[0]));
        return lvt_2_1_.getFormattedText();
    }

    static {
        field_146440_f = new GameSettings.Options[]{GameSettings.Options.FOV};
    }


}
