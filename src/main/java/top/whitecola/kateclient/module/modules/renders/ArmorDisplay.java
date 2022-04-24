package top.whitecola.kateclient.module.modules.renders;

import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;

public class ArmorDisplay extends AbstractModule {
    @Override
    public String getModuleName() {
        return "ArmorDisplay";

    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.VISUAL;
    }
}
