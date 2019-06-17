package com.rollingglory.playground.layer.home;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rollingglory.glorysdk.layer.ActivityGlory;
import com.rollingglory.glorysdk.layouts.menu.MenuGlory;
import com.rollingglory.glorysdk.layouts.menu.OnMenuSelected;
import com.rollingglory.playground.R;
import com.rollingglory.playground.databinding.ActivityHomeBinding;

import javax.inject.Inject;

public class HomeActivity extends ActivityGlory<HomeHeader, ActivityHomeBinding,HomePresenter> implements HomeDelegate{

    @Inject
    HomePresenter presenter;

    @Inject
    ActivityHomeBinding binding;

    @Inject
    HomeHeader header;

    @Override
    public Builder onBuilder() {
        return new Builder(R.layout.activity_home)
                .setSupportToolbar(R.id.toolbar)
                .presenterDelegate(this)
                .presenter(new HomePresenter())
                .headerFactory(new HomeHeader(this))
                .inject(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestCategory();

            }
        });
        binding.buttonCategoryDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestCategoryDb();
            }
        });

        binding.buttonYtsTimeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestYtsTimeout();
            }
        });
        header.addMenu(new MenuGlory(0,"Setting"));
        header.setOnMenuSelected(new OnMenuSelected() {
            @Override
            public boolean menuSelected(MenuItem menuItem) {
                if(menuItem.getItemId() == 0){
                    Toast.makeText(HomeActivity.this,menuItem.getTitle(),Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        header.setImage(R.drawable.ic_android);


    }


    @Override
    public void sizeCategory(int size) {
        Log.i("SUCC","size category "+size);
    }
}
