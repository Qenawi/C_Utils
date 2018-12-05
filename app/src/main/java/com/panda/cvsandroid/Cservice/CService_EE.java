package com.panda.cvsandroid.Cservice;

import android.text.TextUtils;
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
public class CService_EE {
    public final static String ErrorCode = "xXx";

    static <T extends CService_DBase> Boolean IsJasonValid(JsonElement json, T Pattetn) {
        if (json != null && !json.isJsonNull()) {
            return CheckPAttern((T) Pattetn, json);
        }

        return false;
    }

    static CService_Throwable HandelInValidJason(JsonElement json)
    {
        /***
         * Main Target Get Code And Error NAME
         */
        CService_Throwable cService_throwable;
        String Emsg = null;
        int Code = CServiceHelper.ErrorCode.CODE_Default;
        assert json != null;
        if (json.getAsJsonObject().has("Code"))
            Code = (json.getAsJsonObject().get("Code").getAsInt());
        if (json.getAsJsonObject().has("code"))
            Code = (json.getAsJsonObject().get("code").getAsInt());
        if (json.getAsJsonObject().has("Message"))
            Emsg = (json.getAsJsonObject().get("Message").getAsString());
        if (json.getAsJsonObject().has("message"))
            Emsg = (json.getAsJsonObject().get("message").getAsString());
        // After Aquiring MSg and code Code
        if (Emsg == null || TextUtils.isEmpty(Emsg))
        {
            Gson gson = new Gson();
            JSONObject jsonObject = gson.fromJson(json.getAsString(), JSONObject.class);
            Emsg = CrackExeption2(jsonObject);
        }
        //
        cService_throwable = new CService_Throwable(Emsg);
        cService_throwable.setErrorCode(Code);
        cService_throwable.setACtion(Emsg);
        return cService_throwable;
        }
    public static String CrackExeption(final Throwable t)
    {
        String Emsg = t.getMessage();
        try {
            if (t instanceof HttpException) {
                // We had non-2XX http error
                if (((HttpException) t).response() != null) {
                    ResponseBody jsonObject = ((HttpException) t).response().errorBody();

                    JSONObject jsonObject1 = new JSONObject(jsonObject.string());
                    if (Check("Errors", jsonObject1)) {
                        JSONArray array = jsonObject1.getJSONArray("Errors");
                        Emsg = array.getJSONObject(0).getString("errorMsg");
                    } else if (Check("status_message", jsonObject1)) {
                        Emsg = jsonObject1.getString("status_message");
                        Emsg += "";
                    }
                }

            }
        } catch (JSONException | IOException ex) {
            ex.printStackTrace();
        }
        return Emsg;
    }

    private static String CrackExeption2(final JSONObject jsonObject1) {
        String Emsg = "";
        try {
            if (Check("Errors", jsonObject1)) {
                JSONArray array = jsonObject1.getJSONArray("Errors");
                Emsg = array.getJSONObject(0).getString("errorMsg");
            } else if (Check("status_message", jsonObject1)) {
                Emsg = jsonObject1.getString("status_message");
                Emsg = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Emsg;

    }

    private static <T extends CService_DBase> Boolean CheckPAttern(T Pattern, JsonElement e) {

        T lol = (T) (new Gson().fromJson(e, Pattern.getClass()));
        Log.v("Pattern->", Pattern.getClass().getName() + " / " + lol.Is_Data_Good());
        return (lol != null && lol.Is_Data_Good());
    }

    private static Boolean Check(String key, JSONObject e) {
        return e.has(key);
    }
}
