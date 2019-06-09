package com.rollingglory.playground.di;

import com.rollingglory.glorysdk.network.EndpointBuilderGlory;
import com.rollingglory.playground.MainApplication;
import com.rollingglory.playground.network.endpoint.DefaultEndpoint;
import com.rollingglory.playground.network.endpoint.YtsEndpoint;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private MainApplication mainApplication;
    private List list;
    public ApplicationModule(MainApplication mainApplication,List list){
        this.mainApplication = mainApplication;
    }

    @Provides
    public DefaultEndpoint providedEndpointDefault(){
        return new EndpointBuilderGlory("https://www.klikdokter.com")
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .addHeader("X-Api-Auth","wQ0f7FgE9wLhq_XHVHyAT9kL5MdsTTPjpBSKBmydcS73biW8NFimxguGmaDfJmQ96AwE6_KqiWaV9J1mJ1h02icxbh9zKdpylS-MXSPUmGevRPSmwF9hrFX8MR8b5Uo_jRDXrurIWYTEfewhQvpi8n0r731mbkJtaO7DSZN  CeuYh4A 7NqrRNMz4aQovgDw51yvYtBeMAa60YbxgLNVH2Gsw7-xqg3tcKAGERGWdbEEOFgW6l6qkDcJzIItbw")
                .addHeader("X-API-Platform",  "android")
                .addHeader("X-App-Version",  "1.6.27")
                .supportConverterFactoryGson(true)
                .supportAdapterFactoryRxJava(true)
                .create(DefaultEndpoint.class);
    }


    @Provides
    public YtsEndpoint providedEndpointYts(){
        return mainApplication.getYtsEndpoint();
    }

    public List provide(){
        return list;
    }
}
