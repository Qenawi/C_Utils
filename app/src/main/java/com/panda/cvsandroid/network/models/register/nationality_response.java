package com.panda.cvsandroid.network.models.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class nationality_response
{
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<nationality_item> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public List<nationality_item> getData()
    {
        return data;
    }
    public void setData(List<nationality_item> data)
    {
        this.data = data;
    }
}
