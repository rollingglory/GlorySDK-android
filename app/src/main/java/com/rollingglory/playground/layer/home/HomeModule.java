package com.rollingglory.playground.layer.home;


import com.rollingglory.glorysdk.di.layer.ActivityModuleGlory;
import com.rollingglory.playground.databinding.ActivityHomeBinding;

import dagger.Module;

@Module
public class HomeModule extends ActivityModuleGlory<ActivityHomeBinding,HomeActivity,HomePresenter> {

    @Module
    public abstract class Bind{

    }
}
