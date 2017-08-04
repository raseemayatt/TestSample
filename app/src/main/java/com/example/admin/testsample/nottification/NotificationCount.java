
package com.example.admin.testsample.nottification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationCount {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("read_count")
    @Expose
    private Integer readCount;
    @SerializedName("unread_count")
    @Expose
    private Integer unreadCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

}
