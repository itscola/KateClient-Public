package top.whitecola.kateclient.ui.components.notifiction;



import java.util.Vector;

public class NotificationManager {
    protected Vector<Notification> notifications = new Vector<Notification>();

    public void draw(){
        for(int i=0;i<notifications.size();i++){
            notifications.get(i).drawWidget();
            if(notifications.get(i).shouldRemove){
                removeNotification(notifications.get(i));
            }
        }
    }

    public void addNotification(Notification notification){
        notifications.add(notification);
    }

    public void removeNotification(Notification notification){
        notifications.remove(notification);
    }

    public void clear(){
        this.notifications.clear();
    }

}
