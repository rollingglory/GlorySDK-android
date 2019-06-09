package com.rollingglory.playground.layer.home;

import com.rollingglory.glorysdk.layer.PresenterGlory;
import com.rollingglory.glorysdk.utils.CallbackNetworkError;
import com.rollingglory.playground.network.endpoint.DefaultEndpoint;
import com.rollingglory.playground.network.endpoint.YtsEndpoint;
import com.rollingglory.playground.network.param.default_endpoint.request.Category;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter extends PresenterGlory<HomeDelegate> {

    @Inject
    DefaultEndpoint defaultEndpoint;

    @Inject
    YtsEndpoint ytsEndpoint;

    void requestCategory(){

        defaultEndpoint.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    getPresenterDelegate().sizeCategory(response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }



    void requestCategoryDb(){

    }


    void requestYtsTimeout(){

    }
}
