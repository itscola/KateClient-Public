package top.whitecola.kateclient.module.modules.game;

import net.minecraftforge.event.entity.player.AttackEntityEvent;
import top.whitecola.kateclient.module.AbstractModule;
import top.whitecola.kateclient.module.ModuleCategory;

public class DetectCheating extends AbstractModule {

    @Override
    public void onAttackEntity(AttackEntityEvent e) {


        super.onAttackEntity(e);
    }



    @Override
    public ModuleCategory getModuleType() {
        return ModuleCategory.GAME;
    }

    @Override
    public String getModuleName() {
        return "DetectCheating";

    }
}
