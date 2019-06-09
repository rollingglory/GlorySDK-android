package com.rollingglory.playground.network.endpoint;


import com.rollingglory.playground.network.param.default_endpoint.request.Category;

import java.util.List;

import io.reactivex.Observable;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DefaultEndpoint {

    @GET("/api/categories")
    Call<List<Category>> getCategory();

    @GET("/api/categories")
    Observable<List<Category>> getCategoryObservable();

    @GET("/")
    Observable<ResponseBody> getCheckTimeOut();


}
