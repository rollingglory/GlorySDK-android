package com.rollingglory.playground.network.endpoint;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface YtsEndpoint {
    @GET("/")
    Call<ResponseBody> root();
}
