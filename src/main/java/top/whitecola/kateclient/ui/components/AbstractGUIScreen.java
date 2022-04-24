package top.whitecola.kateclient.ui.components;

import net.minecraft.client.gui.GuiScreen;

import java.util.Vector;

public abstract class AbstractGUIScreen extends GuiScreen {
    protected Vector<AbstractComponent> componets = new Vector<AbstractComponent>();

    public void addComponent(AbstractComponent componet){

    }



}
