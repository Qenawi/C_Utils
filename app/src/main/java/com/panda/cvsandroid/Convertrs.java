package com.panda.cvsandroid;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panda.cvsandroid.network.models.login.login_user;

import java.lang.reflect.Type;

public class Convertrs
{
    public static login_user login_data_fromSt(String myList)
    {
        Log.v("Data",myList);
        if (myList == null || TextUtils.isEmpty(myList))
        {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<login_user>()
        {
        }.getType();

        return gson.fromJson(myList, type);
    }
    public static String login_data_to_ST(login_user myList)
    {
        if (myList == null)
        {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<login_user>() {
        }.getType();
        return gson.toJson(myList, type);
    }
}

