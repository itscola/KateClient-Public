package top.whitecola.kateclient.module.modules.visual;

import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;


public class Weather extends AbstractModule {




    @Override
    public String getModuleName() {
        return "Weather";

    }

    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.RENDERS;
    }
}
