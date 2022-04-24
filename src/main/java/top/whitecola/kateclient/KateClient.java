package top.whitecola.kateclient;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import top.whitecola.kateclient.config.HiConfig;
import top.whitecola.kateclient.config.struct.HypixelConfig;
import top.whitecola.kateclient.config.struct.ModuleConfig;
import top.whitecola.kateclient.event.EventManager;
import top.whitecola.kateclient.event.events.*;
import top.whitecola.kateclient.keybinds.HypixelGameSelectorKeybind;
import top.whitecola.kateclient.keybinds.MainMenuInGameKeybind;
import top.whitecola.kateclient.module.ModuleManager;
import top.whitecola.kateclient.module.modules.game.DetectCheating;
import top.whitecola.kateclient.module.modules.game.HypixelSelector;
import top.whitecola.kateclient.module.modules.movment.AutoSprint;
import top.whitecola.kateclient.module.modules.renders.*;
import top.whitecola.kateclient.module.modules.server.AutoGG;
import top.whitecola.kateclient.module.modules.server.AutoTip;
import top.whitecola.kateclient.module.modules.server.LevelHead;
import top.whitecola.kateclient.module.modules.server.LevelTab;
import top.whitecola.kateclient.module.modules.sound.HitSounds;
import top.whitecola.kateclient.module.modules.visual.FullBright;
import top.whitecola.kateclient.module.modules.visual.HitParticle;
import top.whitecola.kateclient.module.modules.visual.ItemPhysic;
import top.whitecola.kateclient.module.modules.visual.Weather;
import top.whitecola.kateclient.services.apis.HypixelAPIWrapper;
import top.whitecola.kateclient.ui.GameUI;
import top.whitecola.kateclient.ui.components.notifiction.NotificationManager;
import top.whitecola.kateclient.ui.widget.WidgetManager;

import java.nio.charset.Charset;

@Mod(modid = KateClient.MODID, version = KateClient.VERSION)
public class KateClient {
    public static final String MODID = "KateClient";
    public static final String VERSION = "1.0";

    private GameUI gameUI = new GameUI();
    private ModuleManager moduleManager = new ModuleManager();
    private NotificationManager notificationManager = new NotificationManager();
    private WidgetManager widgetManager = new WidgetManager();
    private HiConfig<ModuleConfig> moduleConfig = new HiConfig<ModuleConfig>("./KateClient/Modules.json",ModuleConfig.class, Charset.forName("utf8"));
    private HiConfig<HypixelConfig> hypixelConfig = new HiConfig<HypixelConfig>("./KateClient/HypixelConfig.json",HypixelConfig.class, Charset.forName("utf8"));
    public  HypixelAPIWrapper hypixelAPIWrapper = new HypixelAPIWrapper();
    private EventManager eventManager;


    private static KateClient kateClient = null;
    {
        kateClient = this;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        registerEvents();
        registerKeyBinds();
        addModules();
        moduleConfig.config.loadConfigForModules();
    }


    public void registerEvents(){
        MinecraftForge.EVENT_BUS.register(eventManager = EventManager.getEventManager());
        EventManager.getEventManager().addEvent(new MainMenuEvent());
        EventManager.getEventManager().addEvent(new HypixelSelectorMenuEvent());
        EventManager.getEventManager().addEvent(new EventToInvokeModules());
        EventManager.getEventManager().addEvent(new EventToInvokeNotification());
        EventManager.getEventManager().addEvent(new EventToInvokeWidgets());
        EventManager.getEventManager().addEvent(new EventToInvokeHypixelFeatures());

    }

    public void addModules(){
        this.moduleManager.addModule(new AutoSprint());
        this.moduleManager.addModule(new PingDisplay());
        this.moduleManager.addModule(new FullBright());
        this.moduleManager.addModule(new FPSDisplay());
        this.moduleManager.addModule(new HitParticle());
        this.moduleManager.addModule(new LockTime());
        this.moduleManager.addModule(new AutoTip());
        this.moduleManager.addModule(new HypixelSelector());
        this.moduleManager.addModule(new LevelTab());
        this.moduleManager.addModule(new LevelHead());
        this.moduleManager.addModule(new ItemPhysic());
        this.moduleManager.addModule(new HitSounds());



        //not finish under

        this.moduleManager.addModule(new AutoGG());
        this.moduleManager.addModule(new NameHider());
        this.moduleManager.addModule(new CPSDisplay());
        this.moduleManager.addModule(new ArmorDisplay());


        moduleManager.getModuleByName("AutoSprint").enable();
        moduleManager.getModuleByName("PingDisplay").enable();
//        moduleManager.getModuleByName("FPSDisplay").enable();


    }

    public void registerKeyBinds(){
        ClientRegistry.registerKeyBinding(MainMenuInGameKeybind.getInstance());
        ClientRegistry.registerKeyBinding(HypixelGameSelectorKeybind.getInstance());

    }

    public static KateClient getKateClient() {
        return kateClient;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public GameUI getGameUI() {
        return gameUI;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public WidgetManager getWidgetManager() {
        return widgetManager;
    }

    public HiConfig<ModuleConfig> getModuleConfig() {
        return moduleConfig;
    }

    public HiConfig<HypixelConfig> getHypixelConfig() {
        return hypixelConfig;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
