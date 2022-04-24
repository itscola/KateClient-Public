package top.whitecola.kateclient.event;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class EventAdapter {
    private final String id;

    public EventAdapter(String id){
        this.id = id;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e){

    }

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event) {

    }

    @SubscribeEvent
    public void onRender2D(RenderWorldLastEvent event) {

    }


    @SubscribeEvent
    public void tickEvent(TickEvent event) {

    }

    @SubscribeEvent
    public void onRenderOverLay(RenderGameOverlayEvent event) {

    }


    public String getId() {
        return id;
    }

    @SubscribeEvent
    public void renderGameOverlayRETURN() {

    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent e) {

    }

    @SubscribeEvent
    public void onWordRender(RenderWorldEvent e){

    }

    @SubscribeEvent
    public void onLoginIn(FMLNetworkEvent.ClientConnectedToServerEvent e){

    }

    @SubscribeEvent
    public void onLoginOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent e){

    }

    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent e){

    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e){

    }

    @SubscribeEvent
    public void onLivingHurtEvent(LivingHurtEvent e){

    }


    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent e){

    }



    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {

    }

}
