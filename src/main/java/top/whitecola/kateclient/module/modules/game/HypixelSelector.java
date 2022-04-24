package top.whitecola.kateclient.module.modules.game;

import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;

public class HypixelSelector extends AbstractModule {



    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.GAME;
    }

    @Override
    public String getModuleName() {
        return "HypixelSelector";

    }
}
