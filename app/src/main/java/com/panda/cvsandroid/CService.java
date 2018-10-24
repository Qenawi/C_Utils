package com.panda.cvsandroid;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.panda.cvsandroid.network.SingleToneRetrofit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class CService {
    //  main target Get data
    // test Get  nations & cities
    private CompositeDisposable disposable;
    Activity activity;

    CService(Activity activity)
    {
        disposable = new CompositeDisposable();
        this.activity = activity;
    }
    public <T> void FetchData(final T Obj, final HashMap<String, String> Header, final String Url, final HashMap<String, String> requistBody, final CsCallBack f)
    {
        disposable.add(SingleToneRetrofit.<Gson>get_RetrofitCs().CserviceGet(Header, Url, requistBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<JsonElement, SingleSource<JsonElement>>()
                         {
                             @Override
                             public SingleSource<JsonElement> apply(JsonElement jsonElement) throws Exception
                             {

                                 Log.v("CurrentThrid", Url + " " + Thread.currentThread().getName());
                                 String data = jsonElement.getAsJsonObject().get("Status").getAsString();
                                 if (data.equals("Success"))
                                     return Single.just(jsonElement);
                                 else
                                     {
                                      return Single.error(new Throwable(handelerror(jsonElement)));
                                     }
                             }
                         }
                         //d
                )
                .subscribe(s ->
                {
                    Log.v("rese", s.toString());
                    f.Sucess(new Gson().fromJson(s, Obj.getClass()));
                }, f::Faild)
        );
    }
    public <T> void SendData(final T Obj, HashMap<String, String> Header, String Url, HashMap<String, String> requistBody, CsCallBack f) {
        disposable.add(SingleToneRetrofit.<Gson>get_RetrofitCs().CservicePost(Header, Url, requistBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<JsonElement, SingleSource<JsonElement>>()
                         {
                             @Override
                             public SingleSource<JsonElement> apply(JsonElement jsonElement) throws Exception
                             {

                                 Log.v("CurrentThrid", Url + " " + Thread.currentThread().getName());
                                 String data = jsonElement.getAsJsonObject().get("Status").getAsString();
                                 if (data.equals("Success"))
                                     return Single.just(jsonElement);
                                 else
                                 {
                                     handelerror(jsonElement);
                                     return Single.error(new Throwable(handelerror(jsonElement)));
                                 }
                             }
                         }
                )
                .subscribe(s ->
                {
                    Log.v("rese", s.toString());
                    f.Sucess(new Gson().fromJson(s, Obj.getClass()));
                }, e->{f.Faild(new Throwable(CrackExeption(e)));})
        );
    }
    public void Detach() {
        disposable.clear();
    }
    interface CsCallBack
    {
        <T> void Sucess(T Resposne);

        void Faild(Throwable t);

    }
    //---------extract error-----------------------------------
    private String handelerror(JsonElement js)
    {
        String Code = "null",Messege="null";
          if (!js.getAsJsonObject().get("message").isJsonNull())
              Code = js.getAsJsonObject().get("message").getAsString();
          if (!js.getAsJsonObject().get("code").isJsonNull())
            Messege = js.getAsJsonObject().get("code").getAsString();
        Log.v("NUKE",Code+Messege+":0:0:");
        JsonArray Errors=new JsonArray();
        if (js.getAsJsonObject().has("errors"))
            {
           Log.v("Test","errors");
            Errors=js.getAsJsonObject().getAsJsonArray("errors");
            if (!Errors.isJsonNull()&&Errors.size()>0)
            {
            Messege=Errors.get(0).getAsJsonObject().get("errorMsg").getAsString();
            }
            }

             return Code+"/"+Messege;
    }
    public String CrackExeption(Throwable t)
    {
        String Emsg = t.getMessage();
        try {
            if (t instanceof HttpException) {
                // We had non-2XX http error
                if (((HttpException) t).response() != null) {
                    ResponseBody jsonObject = ((HttpException) t).response().errorBody();

                    JSONObject jsonObject1 = new JSONObject(jsonObject.string());
                    JSONArray array = jsonObject1.getJSONArray("Errors");
                    Emsg = array.getJSONObject(0).getString("errorMsg");
                }

            }
        } catch (JSONException | IOException ex) {
            ex.printStackTrace();
        }
        return Emsg;
    }

}
