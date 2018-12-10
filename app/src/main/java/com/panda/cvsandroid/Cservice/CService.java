package com.panda.cvsandroid.Cservice;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.disposables.CompositeDisposable;

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
public class CService implements LifecycleObserver
{
    /**
     * second ->1000 ms
     */
    private Context context;
    private Context app_context;
    private CompositeDisposable disposable;

    public CService(Context C) {
        CService_Throwable throwable = new CService_Throwable(new Throwable("ad"));
        this.context = C;
        app_context = C.getApplicationContext();
        disposable = new CompositeDisposable();
        if (C instanceof LifecycleOwner) {
            ((LifecycleOwner) C).getLifecycle().addObserver(this);
            Log.v("Life Cycle Yea", "ATTACHED");
        }
    }
/*
    public <T extends CService_DBase> void FetchData(final T Obj, final HashMap<String, String> Header, final String Url, final HashMap<String, Object> requistBody, final CsCallBack f) {
        disposable.add(SingleToneRetrofit.<Gson>get_RetrofitCs().CserviceGet(Header, Url, requistBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(jsonElement ->
                        {
                            Log.v("CurrentThrid", Url + " " + Thread.currentThread().getName());
                            if (CService_EE.<T>IsJasonValid(jsonElement, (T) Obj))
                                return Single.just(jsonElement);
                            else {
                                return Single.error(new Throwable(CService_EE.HandelInValidJason(jsonElement)));
                            }
                        }
                        //d
                ).timeout(50, TimeUnit.SECONDS)
                .subscribe(s ->
                        {
                            f.Sucess(new Gson().fromJson(s, Obj.getClass()));
                        }, e ->
                        {
                            // flat map Throw Data Here
                            Log.v("MRQ1", e.getMessage() + "...Error");
                            if (e.getMessage().contains("401")) {
                                String code = e.getLocalizedMessage().split(CService_EE.ErrorCode)[1];
                                Log.v("MRQ", code + "0.0");

                                AlertDialogHandeler.ShowUnAuthDialog(context, new unAutrized_dialog.UnAuthrizedDialogCallBack() {
                                    @Override
                                    public void ReAuthrize() {
                                        ((AppCompatActivity) context).finishAffinity();
                                        ((AppCompatActivity) context).startActivity(new Intent(context, Sigin_In.class));
                                        ((AppCompatActivity) context).finish();
                                    }

                                    @Override
                                    public void ExitAPP() {
                                        ((AppCompatActivity) context).finishAffinity();
                                        ((AppCompatActivity) context).finish();
                                    }
                                }, null);


                            }
                            f.Faild
                                    (new CService_Throwable(CService_EE.CrackExeption(e)));

                        }

                )
        );
    }

    public <T extends CService_DBase> void SendData(final T Obj, HashMap<String, String> Header, String Url, HashMap<String, Object> requistBody, CsCallBack f) {
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
                ).timeout(50, TimeUnit.SECONDS)
                .subscribe(s ->
                {
                    Log.v("rese", s.toString());
                    f.Sucess(new Gson().fromJson(s, Obj.getClass()));
                }, e ->
                {
                    // flat map Throw Data Here
                    Log.v("MRQ1", e.getMessage() + e.getLocalizedMessage());
                    e.printStackTrace();
                    if (e.getMessage().contains("401")) {
                        String code = e.getLocalizedMessage().split(CService_EE.ErrorCode)[1];
                        Log.v("MRQ", code + "0.0");

                        AlertDialogHandeler.ShowUnAuthDialog(context, new unAutrized_dialog.UnAuthrizedDialogCallBack() {
                            @Override
                            public void ReAuthrize() {
                                ((AppCompatActivity) context).finishAffinity();
                                ((AppCompatActivity) context).startActivity(new Intent(context, Sigin_In.class));
                                ((AppCompatActivity) context).finish();
                            }

                            @Override
                            public void ExitAPP() {
                                ((AppCompatActivity) context).finishAffinity();
                                ((AppCompatActivity) context).finish();
                            }
                        }, null);


                    } else if (e.getMessage().contains("406")) {
                        // HAndel Account ACtivation Here nxt Update
                    }
                    f.Faild(new Throwable(CService_EE.CrackExeption(e)));

                })
        );
    }


    public <T extends CService_DBase> void SendData2(final T Obj, HashMap<String, String> Header, String Url, HashMap<String, Object> requistBody, CsCallBack2 f)
    {
        if (!isNetworkAvailable(context))
        {
            CService_Throwable cService_throwable = new CService_Throwable(CServiceHelper.C_Convertor.CodeToMsg(context,CServiceHelper.ErrorCode.CODE_1998_NO_NETWORK_AVAILIBLE));
            cService_throwable.setErrorCode(CServiceHelper.ErrorCode.CODE_1998_NO_NETWORK_AVAILIBLE);
            cService_throwable.setACtion();
            f.Faild(cService_throwable);
            return;
        }
        disposable.add(SingleToneRetrofit.<Gson>get_RetrofitCs().CservicePost2(Header, Url, requistBody).subscribeOn(Schedulers.io())
                .timeout(30, TimeUnit.SECONDS, Single.error(new SocketTimeoutException(CServiceHelper.ActionString.Action_1995_TimeOutConnection)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s ->
                        {
                            Log.v("ResponseHAndel", "ResponseHAndel Code ->  " + s.code() + " ResponseHAndel Msg -> " + s.message());

                            Log.v("rese", s.toString());
                            if (CService_EE.<T>IsJasonValid(s.body(), Obj))
                            {
                                f.Sucess(new Gson().fromJson(s.body(), Obj.getClass()));
                            }
                            else
                                {
                                CService_Throwable ret = CService_EE.HandelInValidJason(s.body());
                                switch (ret.getErrorCode()) {
                                    case CServiceHelper.ErrorCode.CODE_406_UNAcceptable:
                                        ret.setACtion(CServiceHelper.C_Convertor.CodeToMsg(context, CServiceHelper.ErrorCode.CODE_406_UNAcceptable));
                                        break;
                                    case CServiceHelper.ErrorCode.CODE_400_BadArguments:
                                        ret.setACtion(CServiceHelper.C_Convertor.CodeToMsg(context, CServiceHelper.ErrorCode.CODE_400_BadArguments));
                                        break;
                                    case CServiceHelper.ErrorCode.CODE_401_Authentication:
                                        ret.setACtion(CServiceHelper.C_Convertor.CodeToMsg(context, CServiceHelper.ErrorCode.CODE_401_Authentication));
                                        AlertDialogHandeler.ShowUnAuthDialog(context, new unAutrized_dialog.UnAuthrizedDialogCallBack() {
                                            @Override
                                            public void ReAuthrize() {
                                                ((AppCompatActivity) context).finishAffinity();
                                                ((AppCompatActivity) context).startActivity(new Intent(context, Sigin_In.class));
                                                ((AppCompatActivity) context).finish();
                                            }

                                            @Override
                                            public void ExitAPP() {
                                                ((AppCompatActivity) context).finishAffinity();
                                                ((AppCompatActivity) context).finish();
                                            }
                                        }, null);
                                        break;
                                    case CServiceHelper.ErrorCode.CODE_500:
                                        ret.setACtion(CServiceHelper.C_Convertor.CodeToMsg(context, CServiceHelper.ErrorCode.CODE_500));
                                        break;
                                }
                                f.Faild(ret);
                            }
                        }, e ->
                        {
                            int Code = CServiceHelper.ErrorCode.CODE_Default;
                            String Msg = CServiceHelper.ActionString.Action_Default;
                            CService_Throwable cService_throwable = new CService_Throwable(e);
                            if (e instanceof IOException)
                            {
                                Log.v("ExceptionHAndel", "IOException");
                                if (e instanceof SocketTimeoutException)
                                {
                                 Log.v("ExceptionHAndel", "SocketTimeoutException");
                                 Code=CServiceHelper.ErrorCode.CODE_1995_TimeOutConnection;
                                }
                            }
                            else if (e instanceof HttpException)
                            {
                                HttpException exception = (HttpException) e;
                                Code = exception.code();
                                Log.v("ExceptionHAndel", "HttpException");
                                Log.v("ExceptionHAndel", "HttpException Code " + exception.code() + " HttpException Msg " + exception.message());
                                switch (Code)
                                {
                                    case CServiceHelper.ErrorCode.CODE_400_BadArguments :break;
                                    case CServiceHelper.ErrorCode.CODE_401_Authentication:break;
                                    case CServiceHelper.ErrorCode.CODE_406_UNAcceptable:break;
                                }
                            }
                            else
                                {
                                // Un handleable ERRoR
                                }
                            cService_throwable.setErrorCode(Code);
                            cService_throwable.setACtion(Msg);
                            f.Faild(cService_throwable);
                        }
                )
        );
    }


    public interface CsCallBack {
        <T> void Sucess(T Resposne);

        void Faild(Throwable throwable);

    }

    public interface CsCallBack2 {
        <T> void Sucess(T Resposne);

        <E extends Exception> void Faild(E error);

    }

    //--------------------------------------------------------
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach()

    {
        Log.v("ATTACHED", "ATTACHED");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetech() {
        Log.v("Detached", "ATTACHED");
        /*
        view is not ready to handel api calls so cancel them

        AlertDialogHandeler.DismissDialog(null);
        disposable.clear();
    }
 */
    public static boolean isNetworkAvailable (Context context)
    {
        if (connectedToTheNetwork(context))
        {
            try {
                HttpURLConnection urlc = (HttpURLConnection)
                        (new URL("http://clients3.google.com/generate_204")
                                .openConnection());
                urlc.setRequestProperty("User-Agent", "Android");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 204 &&
                        urlc.getContentLength() == 0);
            } catch (IOException e)
            {
                Log.e(CService.class.getName(), "Error checking internet connection", e);
            }
           }
        else
          {
            Log.d(CService.class.getName(), "No network available!");
          }
        return false;
    }
    private static boolean connectedToTheNetwork(Context context)
    {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        }catch (Exception e )
        {
            return false;
        }

    }
}
/*
=
=
=
-
*/