
package com.example.admin.testsample.nottification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NottificationCountResponse {


    @SerializedName("notification_count")
    @Expose
    private NotificationCount notificationCount;



    public NotificationCount getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(NotificationCount notificationCount) {
        this.notificationCount = notificationCount;
    }

}
