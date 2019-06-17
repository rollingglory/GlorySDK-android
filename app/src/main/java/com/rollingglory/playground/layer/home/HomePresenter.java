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



    void get(){
//        managerDatabaseRoom.observerProductEntity(new OnManagerSubscribe<List<ProductEntity>>() {
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onFinish(List<ProductEntity> entity) {
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//        });
//
//
//        ManagerRxGlory.forNetwork(defaultEndpoint.getCategory(), new OnManagerNetwork<List<Category>>() {
//
//            @Override
//            public List<Category> onJob(List<Category> data) {
//                return null;
//            }
//
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onFinish(List<Category> entity) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });

//        ManagerRxGlory.forMultiple(1,2,3,4,new OnManagerMultiple() {
//
//            @Override
//            public Object onJob1() throws IOException {
//                return null;
//            }
//
//            @Override
//            public void onSuccess1(Object data) {
//
//            }
//
//            @Override
//            public void onError1() {
//
//            }
//
//            @Override
//            public Object onJob2(Object data) throws IOException {
//                return null;
//            }
//
//            @Override
//            public void onSuccess2(Object data) {
//
//            }
//
//            @Override
//            public void onError2() {
//
//            }
//
//            @Override
//            public Object onJob3(Object data) throws IOException {
//                return null;
//            }
//
//            @Override
//            public void onSuccess3(Object data) {
//
//            }
//
//            @Override
//            public void onError3() {
//
//            }
//
//            @Override
//            public Object onJob4(Object data) throws IOException {
//                return null;
//            }
//
//            @Override
//            public void onSuccess4(Object data) {
//
//            }
//
//            @Override
//            public void onError4() {
//
//            }
//
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

    }
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
