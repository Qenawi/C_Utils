package com.panda.cvsandroid.network.models.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

public class registerResponse
{
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    @Nullable
    private registerData data;





    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public registerData getData() {
        return data;
    }

    public void setData(registerData data) {
        this.data = data;
    }
}
