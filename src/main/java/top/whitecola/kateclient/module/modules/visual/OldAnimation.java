package top.whitecola.kateclient.module.modules.visual;

import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;

public class OldAnimation extends AbstractModule {
    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.VISUAL;
    }

    @Override
    public String getModuleName() {
        return "OldAnimation";
    }
}
