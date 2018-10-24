package com.panda.cvsandroid.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class verify
{
    /*
{
    "Status": "Error",
    "Code": "400",
    "Message": "المستخدم غير موحود "
}
 */
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
