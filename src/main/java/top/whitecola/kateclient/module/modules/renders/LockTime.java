package top.whitecola.kateclient.module.modules.renders;

import net.minecraftforge.client.event.RenderWorldEvent;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;
import static top.whitecola.kateclient.utils.MCWrapper.*;

public class LockTime extends AbstractModule {

    @Override
    public void onWordRender(RenderWorldEvent e) {
        if(this.isEnabled()) {
            mc.theWorld.setWorldTime(1500);
        }
        super.onWordRender(e);
    }


    @Override
    public String getModuleName() {
        return "LockTime";

    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.RENDERS;
    }
}
