package com.panda.cvsandroid.network.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class login_data
{
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private login_user user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public login_user getUser()
    {
        return user;
    }

    public void setUser(login_user user) {
        this.user = user;
    }
}
