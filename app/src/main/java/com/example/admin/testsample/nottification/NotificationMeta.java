package com.example.admin.testsample.nottification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sojan on 2/24/2017.
 */

public class NotificationMeta {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("job_id")
    @Expose
    private String job_id;

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    @SerializedName("job_title")
    @Expose
    private String job_title;

    public String getName() {
        return name;
    }

    public void setName(String name
    ) {
        this.name = name;
    }
    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

}
