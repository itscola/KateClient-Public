package top.whitecola.kateclient.module.modules.visual;

import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;
import top.whitecola.kateclient.utils.ClientPhysic;

public class ItemPhysic extends AbstractModule {

    @Override
    public void onRender(TickEvent.RenderTickEvent e) {
        if (e.phase == TickEvent.Phase.END)
            ClientPhysic.tick = System.nanoTime();
        super.onRender(e);
    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.VISUAL;
    }

    @Override
    public String getModuleName() {
        return "ItemPhysic";
    }
}
