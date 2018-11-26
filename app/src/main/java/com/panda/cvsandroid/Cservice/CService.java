package com.panda.cvsandroid.Cservice;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.panda.cvsandroid.network.SingleToneRetrofit;

import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * main target create new instance to each view (Activity/Fragment)
 * get parameter and return json response
 * //**
 * what i want to do is to check for return
 * json if it was valid to be used 1
 * or it contain type of error 2
 * if  1-> return json
 * else 2->
 * error has 2 types
 * 1-?
 * don t  have impact on app flow
 * ex -> missing parameter or data don t exist
 * 2-?
 * have direct impact on app flow
 * ex -> login session expired and need to log again  , no network connection
 * 3-?
 * //
 **/
public class CService implements LifecycleObserver {
    private Context context;
    private CompositeDisposable disposable;

    public CService(Context C)
    {
        this.context = C;
        disposable = new CompositeDisposable();
        if (C instanceof LifecycleOwner)
        {
            ((LifecycleOwner) C).getLifecycle().addObserver(this);
            Log.v("Life Cycle Yea", "ATTACHED");
        }

    }

    public <T extends CService_DBase> void FetchData(final T Obj, final HashMap<String, String> Header, final String Url, final HashMap<String, String> requistBody, final CsCallBack f)
    {
        disposable.add(SingleToneRetrofit.<Gson>get_RetrofitCs().CserviceGet(Header, Url, requistBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<JsonElement, SingleSource<JsonElement>>() {
                             @Override
                             public SingleSource<JsonElement> apply(JsonElement jsonElement) throws Exception {
                                 Log.v("CurrentThrid", Url + " " + Thread.currentThread().getName());
                                 if (CService_EE.<T>IsJasonValid(jsonElement, (T) Obj))
                                     return Single.just(jsonElement);
                                 else {
                                     return Single.error(new Throwable(CService_EE.HandelInValidJason(jsonElement)));
                                 }
                             }
                         }
                        //d
                )
                .subscribe(s ->
                        {
                            f.Sucess(new Gson().fromJson(s, Obj.getClass()));
                        }, e ->
                        {
                            // flat map Throw Data Here
                            Log.v("MRQ1", e.getMessage());
                            if (e.getMessage().endsWith("401"))
                            {
                                String code = e.getLocalizedMessage().split(CService_EE.ErrorCode)[1];
                                Log.v("MRQ", code+"0.0");

                            }
                            f.Faild
                                    (new Throwable(CService_EE.CrackExeption(e)));

                        }

                )
        );
    }

    public <T extends CService_DBase> void SendData(final T Obj, HashMap<String, String> Header, String Url, HashMap<String, String> requistBody, CsCallBack f)
    {
        disposable.add(SingleToneRetrofit.<Gson>get_RetrofitCs().CservicePost(Header, Url, requistBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<JsonElement, SingleSource<JsonElement>>() {
                             @Override
                             public SingleSource<JsonElement> apply(JsonElement jsonElement) throws Exception {
                                 Log.v("CurrentThrid", Url + " " + Thread.currentThread().getName());
                                 if (CService_EE.<T>IsJasonValid(jsonElement, (T) Obj))
                                     return Single.just(jsonElement);
                                 else {
                                     return Single.error(new Throwable(CService_EE.HandelInValidJason(jsonElement)));
                                 }
                             }
                         }
                                 )
                .subscribe(s ->
                {
                    Log.v("rese", s.toString());
                    f.Sucess(new Gson().fromJson(s, Obj.getClass()));
                }, e ->
                {
                    // flat map Throw Data Here
                    Log.v("MRQ1", e.getMessage());
                    if (e.getMessage().endsWith("401"))
                    {
                        String code = e.getLocalizedMessage().split(CService_EE.ErrorCode)[1];
                        Log.v("MRQ", code+"0.0");



                    }
                    f.Faild
                            (new Throwable(CService_EE.CrackExeption(e)));

                })
        );
    }

    public interface CsCallBack {
        <T> void Sucess(T Resposne);

        void Faild(Throwable t);

    }

    //--------------------------------------------------------
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach()

    {
        Log.v("ATTACHED", "ATTACHED");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetech()
    {
        Log.v("Detached", "ATTACHED");
        /*
        view is not ready to handel api calls so cancel them
         */
        disposable.clear();
    }
}
/*
=
=
=
-
*/