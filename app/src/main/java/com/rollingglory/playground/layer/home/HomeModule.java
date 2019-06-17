package com.rollingglory.playground.layer.home;


import com.rollingglory.glorysdk.di.layer.ActivityModuleGlory;
import com.rollingglory.playground.databinding.ActivityHomeBinding;

import dagger.Module;

@Module
public class HomeModule extends ActivityModuleGlory<HomeHeader, ActivityHomeBinding,HomePresenter,HomeActivity> {

    @Module
    public abstract class Bind{

    }
}
