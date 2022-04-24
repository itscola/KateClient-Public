package top.whitecola.kateclient.module.modules.renders;

import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;
import top.whitecola.kateclient.ui.widget.AbstractWidget;
import top.whitecola.kateclient.ui.widget.widgets.FPSWidget;

public class FPSDisplay extends AbstractModule {
    protected AbstractWidget fpsWidget = new FPSWidget(0,0,0,0);

    @Override
    public void onEnable() {
        super.onEnable();
        addWidget(fpsWidget);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        removeWidget(fpsWidget);

    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.RENDERS;
    }

    @Override
    public String getModuleName() {
        return "FPSDisplay";

    }
}
