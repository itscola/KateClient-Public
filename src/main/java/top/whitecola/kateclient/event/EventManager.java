package top.whitecola.kateclient.event;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Vector;

public class EventManager {
    private static EventManager eventManager = new EventManager();
    protected Vector<EventAdapter> events = new Vector<EventAdapter>();


    public EventManager() {

    }

    @Deprecated
    public static EventManager getEventManager() {
        return eventManager;
    }


    public void addEvent(EventAdapter adapter) {
        this.events.add(adapter);
    }

    public void removeEvent(EventAdapter adapter) {
        this.events.remove(adapter);
    }

    public <T extends EventAdapter> T getEvent(Class<T> clazz) {
        for (EventAdapter eventAdapter : events) {
            if (eventAdapter.getClass() == clazz) {
                return (T) eventAdapter;
            }
        }

        return null;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onKeyInput(e);
        }
    }

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onRender(e);
        }
    }

    @SubscribeEvent
    public void tickEvent(TickEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.tickEvent(e);
        }
    }

    @SubscribeEvent
    public void onRender2D(RenderWorldLastEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onRender2D(e);
        }
    }

    @SubscribeEvent
    public void onRenderOverLay(RenderGameOverlayEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onRenderOverLay(e);
        }
    }

    public void renderGameOverlayRETURN() {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.renderGameOverlayRETURN();
        }
    }


    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onAttackEntity(e);
        }
    }

    @SubscribeEvent
    public void onWordRender(RenderWorldEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onWordRender(e);
        }
    }

    @SubscribeEvent
    public void onLoginIn(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onLoginIn(e);
        }
    }

    @SubscribeEvent
    public void onLoginOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onLoginOut(e);
        }
    }

    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onChatReceive(e);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onEntityJoinWorld(e);
        }
    }

    @Deprecated
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onLivingHurtEvent(e);
        }
    }


    @Deprecated
    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onLivingAttack(e);
        }
    }


    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
        for (EventAdapter eventAdapter : events) {
            eventAdapter.onLivingUpdate(e);
        }
    }
}
