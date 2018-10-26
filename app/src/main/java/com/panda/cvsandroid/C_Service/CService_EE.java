package com.panda.cvsandroid.C_Service;

import android.util.Log;
import com.google.gson.JsonArray;
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
    public static Boolean IsJasonValid(JsonElement json)
    {
        if (!json.isJsonNull()) {
            if (Check("results", json))

                return true;
        }

        return false;
    }
    public static String HandelInValidJason(JsonElement json)
    {
        String Emsg= "Cant Parse Erroor -_- ";
        if (Check("status_message",json))
        {
            Emsg=json.getAsJsonObject().get("status_message").getAsString();
            Emsg+=" A7la messa 3ala fe5adk ";
        }
        return Emsg;
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
    private static Boolean Check(String key, JsonElement e)
    {
        return !e.getAsJsonObject().get(key).isJsonNull();
    }
    private static Boolean Check(String key, JSONObject e)
    {
        return e.has(key);
    }
}
