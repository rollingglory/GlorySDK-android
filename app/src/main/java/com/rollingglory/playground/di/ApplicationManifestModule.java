package com.rollingglory.playground.di;

import com.rollingglory.playground.layer.home.HomeActivity;
import com.rollingglory.playground.layer.home.HomeModule;
import com.rollingglory.playground.layer.home.HomePresenter;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
abstract class ApplicationManifestModule {
    @ContributesAndroidInjector(modules = {HomeModule.class,HomeModule.Bind.class})
    abstract HomeActivity bindHomeActivity();

    @ContributesAndroidInjector()
    abstract HomePresenter bindHomePresenter();

}
