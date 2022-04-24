package top.whitecola.kateclient.ui.components.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class ScrollList extends GuiListExtended {

    public ScrollList(Minecraft p_i45010_1_, int p_i45010_2_, int p_i45010_3_, int p_i45010_4_, int p_i45010_5_, int p_i45010_6_) {
        super(p_i45010_1_, p_i45010_2_, p_i45010_3_, p_i45010_4_, p_i45010_5_, p_i45010_6_);
    }

    @Override
    public IGuiListEntry getListEntry(int i) {
        return null;
    }

    @Override
    protected int getSize() {
        return 0;
    }
}
