package top.whitecola.kateclient.ui.screen;

import net.minecraft.client.gui.GuiScreen;
import top.whitecola.kateclient.ui.components.buttons.LongRectButton;

import java.io.IOException;

public class MainClickUIMenu extends GuiScreen {


    @Override
    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
    }

    @Override
    public void initGui() {
        int j = this.height / 4 + 48;
        this.buttonList.add(new LongRectButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, "Settings"));
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
    }

    @Override
    protected void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {
        super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_);
    }

    @Override
    protected void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {
        super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);
    }

    @Override
    protected void mouseClickMove(int p_mouseClickMove_1_, int p_mouseClickMove_2_, int p_mouseClickMove_3_, long p_mouseClickMove_4_) {
        super.mouseClickMove(p_mouseClickMove_1_, p_mouseClickMove_2_, p_mouseClickMove_3_, p_mouseClickMove_4_);
    }


}
