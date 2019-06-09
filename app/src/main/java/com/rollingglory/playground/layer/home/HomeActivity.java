package com.rollingglory.playground.layer.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rollingglory.glorysdk.layer.ActivityGlory;
import com.rollingglory.playground.R;
import com.rollingglory.playground.databinding.ActivityHomeBinding;

import javax.inject.Inject;

public class HomeActivity extends ActivityGlory<ActivityHomeBinding,HomePresenter> implements HomeDelegate{



    @Inject
    HomePresenter homePresenter;

    @Inject
    ActivityHomeBinding binding;


    @Override
    public Builder onBuilder() {
        return new Builder(R.layout.activity_home)
                .setSupportToolbar(R.id.toolbar)
                .presenterDelegate(this)
                .presenter(new HomePresenter())
                .inject(true);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.requestCategory();

            }
        });

        binding.buttonCategoryDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.requestCategoryDb();
            }
        });

        binding.buttonYtsTimeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.requestYtsTimeout();
            }
        });
    }


    @Override
    public void sizeCategory(int size) {
        Log.i("SUCC","size category "+size);
    }
}
