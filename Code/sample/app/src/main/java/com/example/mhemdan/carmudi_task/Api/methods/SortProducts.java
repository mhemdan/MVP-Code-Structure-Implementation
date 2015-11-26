package com.example.mhemdan.carmudi_task.Api.methods;

import com.example.mhemdan.carmudi_task.Api.ApiKeys;
import com.example.mhemdan.carmudi_task.Api.ApiUrls;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by mhemdan on 11/19/15.
 */
public interface SortProducts {
    @GET(ApiUrls.SORT_PRODUCTS)
     void execute(@Path(ApiKeys.SORT_TYPE) String sortType, Callback<Response> callback);
}
