package com.panda.cvsandroid.utils;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

public class MyJavaScriptInterface
{
    private Context ctx;

    MyJavaScriptInterface(Context ctx)
    {
        this.ctx = ctx;
    }

    @JavascriptInterface
    public void showHTML(String html)
    {
        Log.v("Html", html);
        String data= Html.fromHtml(html).toString();
        try {
            JSONObject g=new JSONObject(data);
            if (g.has("code"))
            {
                Log.v("Qenawi",g.get("code").toString());
            }
            if (g.has("Status"))
            {
                Log.v("Qenawi",g.get("Status").toString());
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}

