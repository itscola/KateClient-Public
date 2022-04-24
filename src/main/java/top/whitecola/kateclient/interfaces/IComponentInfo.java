package top.whitecola.kateclient.interfaces;

import top.whitecola.kateclient.ui.components.ComponentType;

public interface IComponentInfo {
    ComponentType getComponentType();
    String getComponentName();
    long getStatus();

    void setComponentID(String id);
    String getComponentID();
}
