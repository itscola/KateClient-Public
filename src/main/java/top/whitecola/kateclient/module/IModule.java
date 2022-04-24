package top.whitecola.kateclient.module;

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
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.whitecola.kateclient.ui.widget.AbstractWidget;

public interface IModule {
    void onTick();
    void onRender2D(RenderWorldLastEvent e);

    void onRender(TickEvent.RenderTickEvent e);
    void onRenderOverLay(RenderGameOverlayEvent event);
    void onEntityJoinWorld(EntityJoinWorldEvent e);
    void onEnable();
    void onDisable();

    void enable();
    void disable();
    boolean isEnabled();
    @Deprecated
    void renderGameOverlayRETURN();

    void addOption(ModuleOption option);
    void removeOption(ModuleOption option);

    ModuleCategory getModuleType();
    String getModuleName();
    void optionEnable(String optionName);
    void optionDisable(String optionName);
    ModuleOption getOptionByName(String optionName);
    void onAttackEntity(AttackEntityEvent e);
    void onWordRender(RenderWorldEvent e);
    void onLoginIn(FMLNetworkEvent.ClientConnectedToServerEvent e);
    void onLoginOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent e);
    void onChatReceive(ClientChatReceivedEvent e);

    void addWidget(AbstractWidget widget);
    void removeWidget(AbstractWidget widget);
    void onLivingHurt(LivingHurtEvent e);

    String getDescription();
    void onLivingAttack(LivingAttackEvent e);
    void onLivingUpdate(LivingEvent.LivingUpdateEvent e);
}
