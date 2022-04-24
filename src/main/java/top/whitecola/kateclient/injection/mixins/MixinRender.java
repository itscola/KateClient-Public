package top.whitecola.kateclient.injection.mixins;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.utils.ServerInfoUtils;

@Mixin(Render.class)
public abstract class MixinRender<T extends Entity> {

    @Shadow
    public abstract FontRenderer getFontRendererFromRenderManager();

    @Shadow
    @Final
    protected RenderManager renderManager;

    /**
     * @author White_cola
     * @reason Change Name Tag.
     */
    @Overwrite
    protected void renderLivingLabel(T p_renderLivingLabel_1_, String p_renderLivingLabel_2_, double p_renderLivingLabel_3_, double p_renderLivingLabel_5_, double p_renderLivingLabel_7_, int p_renderLivingLabel_9_) {

        double lvt_10_1_ = p_renderLivingLabel_1_.getDistanceSqToEntity(this.renderManager.livingPlayer);
        if (!(lvt_10_1_ > (double) (p_renderLivingLabel_9_ * p_renderLivingLabel_9_))) {
            FontRenderer lvt_12_1_ = this.getFontRendererFromRenderManager();
            float lvt_13_1_ = 1.6F;
            float lvt_14_1_ = 0.016666668F * lvt_13_1_;
            GlStateManager.pushMatrix();
            GlStateManager.translate((float) p_renderLivingLabel_3_ + 0.0F, (float) p_renderLivingLabel_5_ + p_renderLivingLabel_1_.height + 0.5F, (float) p_renderLivingLabel_7_);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(-lvt_14_1_, -lvt_14_1_, lvt_14_1_);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            Tessellator lvt_15_1_ = Tessellator.getInstance();
            WorldRenderer lvt_16_1_ = lvt_15_1_.getWorldRenderer();
            int lvt_17_1_ = 0;
            if (p_renderLivingLabel_2_.equals("deadmau5")) {
                lvt_17_1_ = -10;
            }

            if (p_renderLivingLabel_1_ instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) p_renderLivingLabel_1_;
                String name = entityPlayer.getGameProfile().getName();


                if (ServerInfoUtils.checkHypixel() && KateClient.getKateClient().getModuleManager().getModuleByName("LevelHead").isEnabled() && KateClient.getKateClient().getModuleManager().getModuleByName("LevelTab").isEnabled()) {
                    String level = KateClient.getKateClient().hypixelAPIWrapper.needPlayerLevelFromCache(name);
                    if (level != null && !level.equals("")) {
                        p_renderLivingLabel_2_ += EnumChatFormatting.BLUE +" [" + level + "]";


                    }
                }
            }

            int lvt_18_1_ = lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2;
            GlStateManager.disableTexture2D();
            lvt_16_1_.begin(7, DefaultVertexFormats.POSITION_COLOR);
            lvt_16_1_.pos((double) (-lvt_18_1_ - 1), (double) (-1 + lvt_17_1_), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            lvt_16_1_.pos((double) (-lvt_18_1_ - 1), (double) (8 + lvt_17_1_), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            lvt_16_1_.pos((double) (lvt_18_1_ + 1), (double) (8 + lvt_17_1_), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            lvt_16_1_.pos((double) (lvt_18_1_ + 1), (double) (-1 + lvt_17_1_), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            lvt_15_1_.draw();
            GlStateManager.enableTexture2D();
            lvt_12_1_.drawString(p_renderLivingLabel_2_, -lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2, lvt_17_1_, 553648127);
            GlStateManager.enableDepth();
            GlStateManager.depthMask(true);
            lvt_12_1_.drawString(p_renderLivingLabel_2_, -lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2, lvt_17_1_, -1);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
        }
    }
}

