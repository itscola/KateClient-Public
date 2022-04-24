package top.whitecola.kateclient.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.whitecola.kateclient.ui.screen.MainClickUIIngame;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Mixin(Minecraft.class)
@SideOnly(Side.CLIENT)
public abstract class MixinMinecraft {
//    @Shadow public abstract void displayGuiScreen(GuiScreen p_displayGuiScreen_1_);

    @Shadow
    private boolean fullscreen;

    @Shadow
    private static List<DisplayMode> macDisplayModes;

    @Shadow
    public int displayWidth;
    @Shadow
    public int displayHeight;

    @Shadow protected abstract void updateDisplayMode() throws LWJGLException;

    @Shadow @Final public DefaultResourcePack mcDefaultResourcePack;

    @Shadow @Final private static ResourceLocation locationMojangPng;

    @Shadow private ResourceLocation mojangLogo;

    @Shadow @Final private static Logger logger;

    @Shadow public abstract void func_181536_a(int p_181536_1_, int p_181536_2_, int p_181536_3_, int p_181536_4_, int p_181536_5_, int p_181536_6_, int p_181536_7_, int p_181536_8_, int p_181536_9_, int p_181536_10_);

    @Shadow public abstract void updateDisplay();

    @Shadow public WorldClient theWorld;

    @Shadow public EntityPlayerSP thePlayer;

    @Shadow public GuiScreen currentScreen;

    @Shadow public GameSettings gameSettings;

    @Shadow public GuiIngame ingameGUI;

    @Shadow public abstract void setIngameNotInFocus();

    @Shadow public boolean skipRenderWorld;

    @Shadow private SoundHandler mcSoundHandler;

    @Shadow public abstract void setIngameFocus();

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.AFTER))
    private void startGame(CallbackInfo ci) {
    }

    /**
     * @author White_cola
     */
    @Overwrite
    private void createDisplay() throws LWJGLException {
        Display.setResizable(true);
        Display.setTitle("KateClient 1.0 beta | Minecraft 1.8.9");

        try {
            Display.create((new PixelFormat()).withDepthBits(24));
        } catch (LWJGLException var4) {

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var3) {
            }

            if (this.fullscreen) {
                this.updateDisplayMode();
            }

            Display.create();
        }

    }



    /**
     * @author White_cola
     * @reason KateClient SplashScreen.
     */
    @Overwrite
    public void drawSplashScreen(TextureManager p_drawSplashScreen_1_) throws LWJGLException {
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int i = scaledresolution.getScaleFactor();
        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i, true);
        framebuffer.bindFramebuffer(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, (double)scaledresolution.getScaledWidth(), (double)scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();
        InputStream inputstream = null;

        try {
            inputstream = this.mcDefaultResourcePack.getInputStream(locationMojangPng);
            this.mojangLogo = p_drawSplashScreen_1_.getDynamicTextureLocation("logo", new DynamicTexture(ImageIO.read(inputstream)));
            p_drawSplashScreen_1_.bindTexture(this.mojangLogo);
        } catch (IOException var12) {
            logger.error("Unable to load logo: " + locationMojangPng, var12);
        } finally {
            IOUtils.closeQuietly(inputstream);
        }

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldrenderer.pos(0.0D, (double)this.displayHeight, 0.0D).tex(0.0D, 0.0D).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos((double)this.displayWidth, (double)this.displayHeight, 0.0D).tex(0.0D, 0.0D).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos((double)this.displayWidth, 0.0D, 0.0D).tex(0.0D, 0.0D).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0.0D).color(255, 255, 255, 255).endVertex();
        tessellator.draw();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int j = 256;
        int k = 256;
        this.func_181536_a((scaledresolution.getScaledWidth() - j) / 2, (scaledresolution.getScaledHeight() - k) / 2, 0, 0, j, k, 255, 255, 255, 255);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        this.updateDisplay();
    }

    /**
     * @author White_cola
     * @reason for some GUI close animation.
     */
    @Overwrite
    public void displayGuiScreen(GuiScreen p_displayGuiScreen_1_) {

        GuiScreen old = this.currentScreen;

        // GUIHandler here later
        if(old instanceof MainClickUIIngame){
            MainClickUIIngame mainClickUIIngame = (MainClickUIIngame) old;
            if(!mainClickUIIngame.isClosed()){
                mainClickUIIngame.closeGUI();
                return;
            }
        }


        if (p_displayGuiScreen_1_ == null && this.theWorld == null) {
            p_displayGuiScreen_1_ = new GuiMainMenu();
        } else if (p_displayGuiScreen_1_ == null && this.thePlayer.getHealth() <= 0.0F) {
            p_displayGuiScreen_1_ = new GuiGameOver();
        }




        GuiOpenEvent event = new GuiOpenEvent((GuiScreen)p_displayGuiScreen_1_);
        if (!MinecraftForge.EVENT_BUS.post(event)) {
            p_displayGuiScreen_1_ = event.gui;
            if (old != null && p_displayGuiScreen_1_ != old) {
                old.onGuiClosed();
            }

            if (p_displayGuiScreen_1_ instanceof GuiMainMenu) {
                this.gameSettings.showDebugInfo = false;
                this.ingameGUI.getChatGUI().clearChatMessages();
            }

            this.currentScreen = p_displayGuiScreen_1_;
            if (p_displayGuiScreen_1_ != null) {
                this.setIngameNotInFocus();
                ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
                int i = scaledresolution.getScaledWidth();
                int j = scaledresolution.getScaledHeight();
                p_displayGuiScreen_1_.setWorldAndResolution(Minecraft.getMinecraft(), i, j);
                this.skipRenderWorld = false;
            } else {




                this.mcSoundHandler.resumeSounds();
                this.setIngameFocus();
            }

        }
    }
}
