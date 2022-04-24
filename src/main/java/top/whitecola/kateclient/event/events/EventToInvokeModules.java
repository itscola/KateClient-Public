package top.whitecola.kateclient.event.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.whitecola.kateclient.KateClient;
import top.whitecola.kateclient.event.EventAdapter;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleManager;

import java.util.Vector;

import static top.whitecola.kateclient.utils.MCWrapper.mc;

public class EventToInvokeModules extends EventAdapter {
    private Vector<AbstractModule> modules;


    public EventToInvokeModules() {
        super(MainMenuEvent.class.getSimpleName());
        modules = KateClient.getKateClient().getModuleManager().getModules();
    }

    @Override
    public void onRender2D(RenderWorldLastEvent event) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onRender2D(event);
        }

        super.onRender2D(event);
    }

    @Override
    public void onRender(TickEvent.RenderTickEvent event) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onRender(event);
        }
        super.onRender(event);
    }

    @Override
    public void tickEvent(TickEvent event) {

        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onTick();
        }

        super.tickEvent(event);
    }

    @Override
    public void onRenderOverLay(RenderGameOverlayEvent event) {
        if(Minecraft.getMinecraft()==null||Minecraft.getMinecraft().theWorld==null || mc.thePlayer==null){
            return;
        }

        if (event.type != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onRenderOverLay(event);
        }
        super.onRenderOverLay(event);
    }

    @Override
    public void renderGameOverlayRETURN() {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.renderGameOverlayRETURN();
        }
        super.renderGameOverlayRETURN();
    }

    @Override
    public void onAttackEntity(AttackEntityEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onAttackEntity(e);
        }
        super.onAttackEntity(e);
    }


    @Override
    public void onWordRender(RenderWorldEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onWordRender(e);
        }
        super.onWordRender(e);
    }

    @Override
    public void onLoginIn(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onLoginIn(e);
        }
        super.onLoginIn(e);
    }

    @Override
    public void onLoginOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onLoginOut(e);
        }

        super.onLoginOut(e);
    }

    @Override
    public void onChatReceive(ClientChatReceivedEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onChatReceive(e);
        }
        super.onChatReceive(e);
    }

    @Override
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onEntityJoinWorld(e);
        }
        super.onEntityJoinWorld(e);
    }

    @Override
    public void onLivingHurtEvent(LivingHurtEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onLivingHurt(e);
        }
        super.onLivingHurtEvent(e);
    }

    @Override
    public void onLivingAttack(LivingAttackEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onLivingAttack(e);
        }
        super.onLivingAttack(e);
    }


    @Override
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
        for(AbstractModule module : modules){
            if(!module.isEnabled())
                continue;
            module.onLivingUpdate(e);
        }
        super.onLivingUpdate(e);
    }
}
