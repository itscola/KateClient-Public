package top.whitecola.kateclient.module.modules.renders;

import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;

public class CPSDisplay extends AbstractModule {
    @Override
    public String getModuleName() {
        return "CPSDisplay";

    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.VISUAL;
    }

}
