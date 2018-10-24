package com.panda.cvsandroid.network.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class login_response
{
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private login_data data;









    public String getStatus()
    {
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

    public login_data getData() {
        return data;
    }

    public void setData(login_data data) {
        this.data = data;
    }

}
