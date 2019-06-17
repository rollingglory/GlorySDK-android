package com.rollingglory.playground;

import com.rollingglory.glorysdk.layer.ApplicationGlory;
import com.rollingglory.glorysdk.network.EndpointBuilderGlory;
import com.rollingglory.playground.di.ApplicationModule;

import com.rollingglory.playground.di.DaggerApplicationComponent;
import com.rollingglory.playground.network.endpoint.DefaultEndpoint;
import com.rollingglory.playground.network.endpoint.YtsEndpoint;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends ApplicationGlory {
    private DefaultEndpoint defaultEndpoint;
    private YtsEndpoint ytsEndpoint;

    @Override
    public Builder onBuilder() {
        return new Builder().setFontPath("path");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initRealm();
        initEndpoint();
    }

    private void initDagger(){
        DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this,null))
                .build()
                .inject(this);
    }
    private void initRealm(){
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initEndpoint(){

        defaultEndpoint =  new EndpointBuilderGlory(BuildConfig.ENDPOINT_DEFAULT)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .addHeader("X-Api-Auth",BuildConfig.ENDPOINT_DEFAULT_TOKEN_AUTH)
                .addHeader("X-API-Platform",  BuildConfig.ENDPOINT_DEFAULT_TOKEN_PLATFORM)
                .addHeader("X-App-Version",  "1.6.27")
                .supportAdapterFactoryRxJava(true)
                .supportConverterFactoryGson(true)
                .create(DefaultEndpoint.class);

        ytsEndpoint =  new EndpointBuilderGlory("https://yts.am")
                .create(YtsEndpoint.class);

    }



    public DefaultEndpoint getDefaultEndpoint(){
        return defaultEndpoint;
    }

    public YtsEndpoint getYtsEndpoint() {
        return ytsEndpoint;
    }

}
