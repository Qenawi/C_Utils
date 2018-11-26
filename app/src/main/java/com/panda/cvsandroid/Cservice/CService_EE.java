package com.panda.cvsandroid.Cservice;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * CService Error Extractor Class
 * Target
 * (1)
 * Check Json for its Validation for use
 * or it contain error
 * (2)
 * Get Error Type $2 types
 * 2 .1 view can handel it-> so pass Throwable or txt to view to show toast and handel error
 * 2 .2 error will impact main app flow (nO Connection or  authentication problem )
 */
public class CService_EE
{
   public final static String ErrorCode="xXx";
    static <T extends CService_DBase >Boolean IsJasonValid(JsonElement json, T Pattetn)
    {
        if (json!=null&&!json.isJsonNull())
        {
            return CheckPAttern((T)Pattetn, json);
        }

        return false;
    }
    static String HandelInValidJason(JsonElement json)
    {
        String Emsg= "Cant Parse Erroor -_- ";
        String Code="E";
        assert json!=null;
        if (json.getAsJsonObject().has("code"))
        Code=(ErrorCode)+(json.getAsJsonObject().get("code").getAsString());
        return (Code);
    }
    public static String CrackExeption(Throwable t)
    {
        String Emsg = t.getMessage();
        try {
            if (t instanceof HttpException)
            {
                // We had non-2XX http error
                if (((HttpException) t).response() != null)
                {
                    ResponseBody jsonObject = ((HttpException) t).response().errorBody();

                    JSONObject jsonObject1 = new JSONObject(jsonObject.string());
                    if(Check("Errors",jsonObject1))
                    {
                        JSONArray array = jsonObject1.getJSONArray("Errors");
                        Emsg = array.getJSONObject(0).getString("errorMsg");
                    }
                    else if (Check("status_message",jsonObject1))
                    {
                        Emsg=jsonObject1.getString("status_message");
                        Emsg+=" A7la messa 3ala fe5adk ";
                    }
                }

            }
        } catch (JSONException | IOException ex)
        {
            ex.printStackTrace();
        }
        return Emsg;
    }
    private static <T extends CService_DBase> Boolean CheckPAttern(T Pattern, JsonElement e)
    {

        T lol=(T)(new Gson().fromJson(e, Pattern.getClass()));
        Log.v("Pattern->",Pattern.getClass().getName()+" / " +lol.Is_Data_Good());
        return (lol!=null&&lol.Is_Data_Good());
    }
    private static Boolean Check(String key, JSONObject e)
    {
        return e.has(key);
    }
}
