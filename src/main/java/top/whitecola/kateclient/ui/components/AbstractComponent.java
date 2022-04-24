package top.whitecola.kateclient.ui.components;

import top.whitecola.kateclient.interfaces.IComponentInfo;
import top.whitecola.kateclient.interfaces.IComponentInteract;

public class AbstractComponent implements IComponentInteract, IComponentInfo {
    public float x;
    public float y;

    public float x2;
    public float y2;

    public long status = 0;
    public String componentID;
    public String content;

    public AbstractComponent(float x, float y, float x2, float y2, String id){
        this.x = x;
        this.y = y;

        this.x2 = x2;
        this.y2 = y2;

        setComponentID(id);
    }



    @Override
    public void onTick(float mouseX, float mouseY){
        onClicking(mouseX,mouseY);
        onDragging(mouseX,mouseY);
        onHovering(mouseX,mouseY);
        drawScreen();
    }

    @Override
    public void setDisplay(boolean display) {
        if(!display && status!=0)
            status = 0;

        status = 1;
    }

    @Override
    public void drawScreen() {
        if(status==0) return;
    }

    @Override
    public void onHovering(float mouseX, float mouseY) {
        if(!isHovered(mouseX,mouseY))
            return;


    }

    @Override
    public void onClicking(float mouseX, float mouseY) {
        if(!isClicked(mouseX,mouseY))
            return;
    }

    @Override
    public void onDragging(float mouseX, float mouseY) {
        if(!isDragging(mouseX,mouseY))
            return;
    }


    @Override
    public boolean isHovered(float mouseX, float mouseY) {

        return false;
    }

    @Override
    public boolean isClicked(float mouseX, float mouseY) {
        return false;
    }

    @Override
    public boolean isDragging(float mouseX, float mouseY) {
        return false;
    }


    @Override
    public ComponentType getComponentType() {
        return ComponentType.BUTTON;
    }

    @Override
    public String getComponentName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public long getStatus() {
        return status;
    }

    @Override
    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    @Override
    public String getComponentID() {
        return componentID;
    }
}
