package com.panda.cvsandroid.Cservice;

import com.google.gson.JsonElement;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface CserviceApi
{
    @GET
    Single<JsonElement> CserviceGet(@HeaderMap HashMap<String, String> headers, @Url String url, @QueryMap HashMap<String, String> Data);

    @POST
    Single<JsonElement> CservicePost(@HeaderMap HashMap<String, String> headers, @Url String url, @Body HashMap<String, String> Data);
}
