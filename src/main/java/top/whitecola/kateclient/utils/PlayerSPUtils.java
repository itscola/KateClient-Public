package top.whitecola.kateclient.utils;

import net.minecraft.client.gui.GuiKeyBindingList;

import static top.whitecola.kateclient.utils.MCWrapper.mc;

public class PlayerSPUtils {
    public static boolean isMoving() {



        if(mc==null || mc.thePlayer==null || mc.thePlayer.movementInput==null){
            return false;
        }

        if (mc.thePlayer.movementInput.moveForward != 0f || mc.thePlayer.movementInput.moveStrafe != 0f) {
            return true;
        }
        return false;
    }

    public static boolean isSneaking() {

        if(mc==null || mc.thePlayer==null){
            return false;
        }

        if ((mc.thePlayer.isSneaking())) {
            return true;
        }
        return false;
    }



}
