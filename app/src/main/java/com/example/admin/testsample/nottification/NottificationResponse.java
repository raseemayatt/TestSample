
package com.example.admin.testsample.nottification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NottificationResponse {


    @SerializedName("notification_list")
    @Expose
    private List<NotificationList> notificationList = null;

    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("notification_count")
    @Expose
    private NotificationCount notificationCount;



    public List<NotificationList> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<NotificationList> notificationList) {
        this.notificationList = notificationList;
    }



    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public NotificationCount getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(NotificationCount notificationCount) {
        this.notificationCount = notificationCount;
    }

}
