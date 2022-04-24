package top.whitecola.kateclient.module.modules.renders;

import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;

public class AttackRangeDisplay extends AbstractModule {
    @Override
    public String getModuleName() {
        return "AttackRangeDisplay";

    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.VISUAL;
    }
}
