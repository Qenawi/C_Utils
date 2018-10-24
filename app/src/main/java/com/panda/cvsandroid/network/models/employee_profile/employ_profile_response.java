package com.panda.cvsandroid.network.models.employee_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class employ_profile_response
{

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private employData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public employData getData() {
        return data;
    }

    public void setData(employData data)
    {
        this.data = data;
    }
}
