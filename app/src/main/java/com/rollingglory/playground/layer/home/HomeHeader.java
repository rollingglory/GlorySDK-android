package com.rollingglory.playground.layer.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.rollingglory.glorysdk.layouts.HeaderGlory;
import com.rollingglory.playground.R;


public class HomeHeader extends HeaderGlory.Factory {
    Toolbar toolbar;
    ImageView image;
    public HomeHeader(Context context) {
        super(context);
    }

    @Override
    public AppBarLayout onCreateAppBarLayout(ViewGroup viewGroup) {
        AppBarLayout appBarLayout = (AppBarLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_factory_home,viewGroup,false);

        toolbar = appBarLayout.findViewById(com.rollingglory.glorysdk.R.id.toolbar);
        image = appBarLayout.findViewById(R.id.image);
        setAppBarLayout(appBarLayout);
        setToolbar(toolbar);

        return appBarLayout;
    }

    void setImage(@DrawableRes int resId){
        image.setImageResource(resId);
    }
}
