package com.panda.cvsandroid.network.models.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class registerData
{
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
