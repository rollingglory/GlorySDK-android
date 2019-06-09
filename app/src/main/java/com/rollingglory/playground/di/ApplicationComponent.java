package com.rollingglory.playground.di;

import com.rollingglory.playground.MainApplication;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {ApplicationManifestModule.class,ApplicationModule.class, AndroidSupportInjectionModule.class})
public interface ApplicationComponent extends AndroidInjector<MainApplication> {

    @Override
    void inject(MainApplication instance);

}
