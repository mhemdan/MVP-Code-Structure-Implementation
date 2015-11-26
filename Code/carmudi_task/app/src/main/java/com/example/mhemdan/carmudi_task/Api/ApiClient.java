package com.example.mhemdan.carmudi_task.Api;


import android.content.Context;
import android.util.Log;

import com.example.mhemdan.carmudi_task.Api.methods.GetProducts;
import com.example.mhemdan.carmudi_task.Api.methods.SortProducts;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


/**
 * Created by mohammed on 6/30/15.
 */
public class ApiClient {
    private static ApiClient apiClient;
    private RestAdapter restAdapter;
    public static synchronized ApiClient getInstance(){
        if(apiClient==null){
                apiClient = new ApiClient();
            return apiClient;
        }else{
            return apiClient;
        }
    }

    private ApiClient(){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(360 , TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(360, TimeUnit.SECONDS);
        okHttpClient.setFollowSslRedirects(true);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiUrls.BASE_URL)
                .setClient(new OkClient(okHttpClient))
                .build();

    }




    public void getProducts(int pageNumber, final ApiCallBack apiCallBack){
        GetProducts getProducts = restAdapter.create(GetProducts.class);
        Callback callback = new Callback() {


            @Override
            public void success(Object o, Response response) {
                try {
                    apiCallBack.onSuccess(new JSONObject(new String(((TypedByteArray) response.getBody()).getBytes())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                apiCallBack.onFailure(error);
            }
        };
        getProducts.execute(pageNumber,callback);

    }
    public void getProductsWithSort(String sortType, final ApiCallBack apiCallBack){
        SortProducts sortProducts = restAdapter.create(SortProducts.class);
        Callback callback = new Callback() {


            @Override
            public void success(Object o, Response response) {
                try {
                    apiCallBack.onSuccess(new JSONObject(new String(((TypedByteArray) response.getBody()).getBytes())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                apiCallBack.onFailure(error);
            }
        };
        sortProducts.execute(sortType,callback);
    }

}

