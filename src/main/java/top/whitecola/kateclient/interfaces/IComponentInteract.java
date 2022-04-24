package top.whitecola.kateclient.interfaces;

public interface IComponentInteract {

    boolean isHovered(float mouseX, float mouseY);
    boolean isClicked(float mouseX, float mouseY);
    boolean isDragging(float mouseX, float mouseY);

    void onHovering(float mouseX, float mouseY);
    void onClicking(float mouseX, float mouseY);
    void onDragging(float mouseX, float mouseY);

    void onTick(float mouseX, float mouseY);
    void drawScreen();

    void setDisplay(boolean display);
}
