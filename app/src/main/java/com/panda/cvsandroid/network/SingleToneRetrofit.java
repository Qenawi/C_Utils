package com.panda.cvsandroid.network;


import com.panda.cvsandroid.CserviceApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleToneRetrofit {
    static final String BaseUrl = "Qenawi";



    public static Retrofit get_Retrofit()
    {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor).
                connectTimeout(1, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES);
        ;
        return new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

    }
    public static CserviceApi get_RetrofitCs()
    {



        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor).
                connectTimeout(1, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES);
        ;
        return new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build().create(CserviceApi.class);

    }
}
