
<img align="left" width="60" height="60" src="https://avatars1.githubusercontent.com/u/30823810?s=100&v=4"> </img>

**RollingGlory** is Company or Creative Digital Media studio based in Bandung, Indonesia.


&nbsp;
&nbsp;
## GlorySDK
GlorySDK is bundle API for Utility, Network, UI, Component and Architecture. Use package jetpack androidx and support kotlin in your project. 

#### Link for Configuration Gradle
* [Configuration gradle for Module](#configuration-gradle-for-module)
* [Configuration gradle for Project](#configuration-gradle-for-project)
#### Link for base GlorySDK
* [Architecture GlorySDK](#architecture-glorysdk)
* [Depedency Injection Support AndroidX](#depedency-injection-support-androidx)
* [Header Factory](#header-factory)
* [Drawer Factory](#drawer-factory)
* [RxGlory](#rxglory)
* [Endpoint Glory Builder](#endpoint-glory-builder)
#### Link for Quick Start 
* [First Configuration Depedency Injection](#first-configuration-depedency-injection)
* [Second Configuration Style](#second-configuration-style)
#### Link for Implementation Activity & Fragment
* [Implementation for Activity](#implementation-for-activity)
* [Implementation for Fragment](#implementation-for-fragment)
#### Link for Implementation RxGlory
* [JobGloryRx](#implementation-jobgloryrx)
* [JobMultipleRxGlory](#implementation-jobmultiplerxglory)
* [JobNetworkRxGlory](#implementation-jobnetworkrxglory)
* [JobNetworkMultipleRxGlory](#implementation-jobnetworkmultiplerxglory)
* [JobNetworkWithRealmRxGlory](#implementation-jobnetworkwithrealmrxglory)
* [JobRealmRxGlory](#implementation-jobrealmrxglory)


#### Custom Factory
* [Custom Header Factory](#custom-header-factory)
* [Custom Drawer Factory](#custom-drawer-factory)
#### Link for Convention 
* [Convention Package](#convention-package)
* [Convention Endpoint in Gradle for Project](#convention-endpoint-in-gradle-for-project)

&nbsp;
---
&nbsp;
#### Configuration Gradle for Module

~~~gradle
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'
apply plugin: 'realm-android'


repositories {
    maven {
        url  "https://dl.bintray.com/rollingglory/GlorySDK-android"
    }
}

android {
    //please enable data binding
    dataBinding {
        enabled = true
    }
}


dependencies {

    implementation 'com.rollingglory:glorysdk-android:0.0.1-alpha03'
    
    
    //please include 
    
    //org.jetbrains.kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.31"

    //io.reactivex.rxjava2
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.2.8'

    //com.squareup.retrofit2
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.5.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'

    //com.google.*
    implementation 'com.google.code.gson:gson:2.8.4'
    implementation 'com.google.android.material:material:1.0.0'

    //com.google.dagger
    implementation 'com.google.dagger:dagger:2.22.1'
    kapt 'com.google.dagger:dagger-compiler:2.22.1'
    implementation 'com.google.dagger:dagger-android:2.22.1'
    implementation 'com.google.dagger:dagger-android-support:2.22.1'
    kapt 'com.google.dagger:dagger-android-processor:2.22.1'
    implementation 'javax.annotation:javax.annotation-api:1.3.2' //javax.annotation:jsr250-api:1.0 move to javax.annotation-api:1.3.2


    //androidx
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.multidex:multidex:2.0.1'

    //androidx.room.*
    implementation "androidx.room:room-runtime:2.1.0-rc01"
    annotationProcessor "androidx.room:room-compiler:2.1.0-rc01"
    implementation "androidx.room:room-rxjava2:2.1.0-rc01"
    implementation "androidx.room:room-testing:2.1.0-rc01"
    
    //com.jakewharton.retrofit
    implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'
}
~~~


#### Configuration Gradle for Project
~~~gradle

buildscript {
    ext.kotlin_version = '1.3.31'
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.1'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.31"
        classpath "io.realm:realm-gradle-plugin:5.11.0"
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4'
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url  "https://dl.bintray.com/rollingglory/GlorySDK-android"
        }
        maven { url 'https://maven.fabric.io/public' }
    }
}

~~~

#### Gradle for Properties (#Gradle-for-Project)
~~~gradle
android.useAndroidX=true
android.databinding.enableV2=true
android.enableJetifier=true
~~~


## Base GlorySDK
#### Architecture GlorySDK
| Class | Migration Of | Role |
| ------ | ------ | ------ |
|ApplicationGlory | Application | Application |
| ActivityGlory | AppCompatActivity | Activity |
| FragmentGlory | Fragment | Fragment |
| PresenterGlory<t1 extends PresenterDelegateGlory> | - | Presenter|
| PresenterDelegateGlory | - | Presenter Delegate |
| ModuleActivityGlory<t1 extends ActivityGlory> | - | Provided Module Activity |
| ModuleBindingOnActivityGlory<t1 extends ActivityGlory,t2 extends ViewDataBinding> | - | Provided Module Binding On Activity |
| ModulePresenterOnActivityGlory<t1 extends ActivityGlory,t2 extends PresenterGlory> | - | Provided Module Presenter On Activity |
| ModuleHeaderOnActivityGlory<t1 extends ActivityGlory,t2 extends HeaderGlory.Factory> | - | Provided Module Header Factory On Activity |
| ModuleDrawerOnActivityGlory<t1 extends ActivityGlory,t2 extends DrawerGlory.Factory> | - | Provided Module Drawer Factory On Activity |
| ModuleFragmentGlory<t1 extends FragmentGlory> | - | Provided Module Fragment |
| ModuleBindingOnFragmentGlory<t1 extends FragmentGlory,t2 extends ViewDataBinding> | - | Provided Module Binding On Fragment |
| ModulePresenterOnFragmentGlory<t1 extends FragmentGlory,t2 extends PresenterGlory> | - | Provided Module Presenter On Fragment |
| ModuleHeaderOnFragmentGlory<t1 extends FragmentGlory,t2 extends HeaderGlory.Factory> | - | Provided Module Header Factory On Fragment |
| ModuleDrawerOnFragmentGlory<t1 extends FragmentGlory,t2 extends DrawerGlory.Factory> | - | Provided Module Drawer Factory On Fragment |
    
    
#### Depedency Injection Support AndroidX
| Class | Migration Of | Role |
| ------ | ------ | ------ |
| AndroidInjectionX | AndroidInjection | Injection
| HasActivityInjectorX | HasActivityInjector | Mark Activity Has Inject|
| HasFragmentInjectorX | HasFragmentInjector | Mark Fragment Has Inject |
| HasPresenterInjectorX | - | Mark Presenter Has Inject |


#### Header Factory
Header Factory is header module ui for layer activity or fragment;
#### Drawer Factory
Header Factory is drawer module ui for layer activity or fragment;
#### Style
- ThemeGlory
- ThemeGlory.NoActionBar
- ThemeGlory.NoActionAndStatusBar
- ThemeGlory.AppBarOverlay

#### RxGlory
RxGlory is custom from RxJava with subscribe and observable, use RxGlroy for Job, Multile Job, Network, Multiple Network and Network with Realm. 

#### Endpoint Glory Builder
EndpointBuilderGlory is Builder network for retrofit.

## Quick Implementation

#### First Configuration Depedency Injection

~~~java
//ApplicationComponent.java
@Component(modules = {ModuleApplicationGlory.class, 
        ManifestApplicationModule.class, 
        ProvidedApplicationModule.class, 
        AndroidSupportInjectionModule.class})

public interface ApplicationComponent extends AndroidInjector<MainApplication> {
    @Override
    void inject(MainApplication application);
}


//ApplicationComponent.java
@Component(modules = {ModuleApplicationGlory.class, 
        ManifestApplicationModule.class, 
        ProvidedApplicationModule.class, 
        AndroidSupportInjectionModule.class})

public interface ApplicationComponent extends AndroidInjector<MainApplication> {
    @Override
    void inject(MainApplication application);
}

~~~

~~~java
//ProvidedApplicationModule.java
@Module
public class ProvidedApplicationModule {
    private MainApplication mainApplication;

    public ProvidedApplicationModule(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }

    @Provides
    public ReqresEndpoint providedEndpointDefault(){
        return new EndpointBuilderGlory("https://reqres.id")
                .supportConverterFactoryGson(true)
                .supportAdapterFactoryRxJava(true)
                .create(ReqresEndpoint.class);
    }

}
~~~

~~~java
@Module
//ManifestApplicationModule.java
abstract class ManifestApplicationModule {
    @ContributesAndroidInjector(modules = {HomeModule.class,
            HomeModule.Binding.class,
            HomeModule.Presenter.class,
            HomeModule.Contribute.class})
    abstract HomeActivity bindHomeActivity();


    @ContributesAndroidInjector()
    abstract HomePresenter bindHomePresenter();

}
~~~


~~~java
public class MainApplication extends ApplicationGlory {
    private ReqresEndpoint defaultEndpoint;


    @Override
    public Builder onBuilder() {
        return new Builder().setFontPath("path");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initRealm();

    }

    private void initDagger(){
        DaggerApplicationComponent
                .builder()
                .providedApplicationModule(new ProvidedApplicationModule(this))
                .build()
                .inject(this);
    }
   
    public ReqresEndpoint getDefaultEndpoint(){
        return defaultEndpoint;
    }


}
~~~
#### Second Configuration Style
Use theme ThemeGlory.NoActionBar
~~~xml
 <?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.rollingglory.rollingglorysdk">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission
        android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="18" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/ThemeGlory"
        android:name=".MainApplication">

        <activity
            android:name=".layer.home.HomeActivity"
            android:label="@string/app_name"
            android:theme="@style/ThemeGlory.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
~~~

## Implementation for Activity
#### Activity
~~~java
class HomeActivity extends ActivityGlory implements HomeDelegate{

    @Inject
    HomePresenter presenter;

    @Inject
    ActivityHomeBinding binding;

    @Inject
    ToolbarHeader header;

    @Inject
    HomeDrawer drawer;

    @Override
    public LayerBuilder onBuilder() {
        return new LayerBuilder(R.layout.activity_home)
                .presenterDelegate(this)
                .presenter(new HomePresenter())
                .drawerFactory(new HomeDrawer(this))
                .headerFactory(new ToolbarHeader(this))
                .inject(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding.buttonSingleUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.singleUser();
            }
        });

        drawer.addMenu(new MenuGlory(1,"menu 2"));
        drawer.setOnMenuSelected(new OnMenuSelected() {
            @Override
            public boolean menuSelected(MenuItem menuItem) {
                Toast.makeText(HomeActivity.this,menuItem.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            }
        });

        binding.buttonListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.listUser();
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


    }


    @Override
    public void userResponse(UserItem userItem) {
        //todo user item response
    }

    @Override
    public void userResponseError(String e) {
        //todo error request user item 
    }
}
~~~
#### Presenter 
~~~java
 class HomePresenter extends PresenterGlory<HomeDelegate> {



    @Inject
    ReqresEndpoint reqresEndpoint;


    public void singleUser(){
        new JobNetworkRxGlory().subscribe(reqresEndpoint.singleUser(1), new OnJobNetworkGloryRx<ReqresUser>() {
            @Override
            public ReqresUser onJob(ReqresUser reqresUser) throws IOException {
                //todo job request
                return reqresUser;
            }

            @Override
            public void onSuccess(ReqresUser reqresUser) {
                getPresenterDelegate().userResponse(reqresUser.userItem);
            }

            @Override
            public void onError(Throwable e) {
                //todo error, if request endpoint error adapter, parser json and exception
            }

            @Override
            public void onErrorNetwork(String e) {
                //todo error, if request endpooint error http code, timeout and illegal exception
                getPresenterDelegate().userResponseError(e);
            }
        });
    }
}
~~~
#### Presenter Delegate
~~~java
interface HomeDelegate extends PresenterDelegateGlory {
    void userResponse(UserItem userItem);
    void userResponseError(String e);
}

~~~


#### Module Provided
~~~java
@Module
public class HomeModule extends ModuleActivityGlory {



    @Module
    public static class Drawer extends ModuleDrawerOnActivityGlory<HomeActivity, HomeDrawer> {}

    @Module
    public static class Binding extends ModuleBindingOnActivityGlory<HomeActivity, ActivityHomeBinding> {}

    @Module
    public static class Header extends ModuleHeaderOnActivityGlory<HomeActivity, ToolbarHeader> {}

    @Module
    public static class Presenter extends ModulePresenterOnActivityGlory<HomeActivity, HomePresenter> {}
}

~~~


## Implementation for Fragment
~~~java
~~~

## Implementation RxGlory

#### Implementation JobGloryRx
~~~java
class HomePresenter extends PresenterGlory<HomeDelegate> {
 void populateUser(){
     new JobGloryRx().subscribe(new OnJobGloryRx<List<UserItem>>() {
        @Override
        public List<UserItem> onJob() throws IOException {
            //todo job
            return new ArrayList<>();
        }

        @Override
        public void onSuccess(List<UserItem> data) {
            //todo success and update ui
        }

        @Override
        public void onError(Throwable e) {
            //todo error
        }
    });
    }
}
~~~

#### Implementation JobMultipleRxGlory
~~~java
class HomePresenter extends PresenterGlory<HomeDelegate> {
   
    void populateAndFind(){
         new JobMultipleRxGlory().subscribe(new OnJobMultipleRxGlory<List<UserItem>,List<ResourceItem>,UserItem,ResourceItem>(){

            @Override
            public List<UserItem> onJob1() throws IOException {
                //todo job first
                return new ArrayList<>();
            }

            @Override
            public void onSuccess1(List<UserItem> data) {
                //todo success job first and update ui
            }

            @Override
            public void onError1(Throwable e) {
                //todo error job first
            }

            @Override
            public List<ResourceItem> onJob2(List<UserItem> data) throws IOException {
                //todo job second
                return new ArrayList<>();
            }

            @Override
            public void onSuccess2(List<ResourceItem> data) {
                //todo success job second and update ui
            }

            @Override
            public void onError2(Throwable e) {
                //todo error job second
            }

            @Override
            public UserItem onJob3(List<ResourceItem> data) throws IOException {
                //todo job third
                return new UserItem();
            }

            @Override
            public void onSuccess3(UserItem data) {
                //todo success job third and update ui
            }

            @Override
            public void onError3(Throwable e) {
                //todo error job third
            }

            @Override
            public ResourceItem onJob4(UserItem data) throws IOException {
                //todo job fourth
                return new ResourceItem();
            }

            @Override
            public void onSuccess4(ResourceItem data) {
                //todo success job fourth
            }

            @Override
            public void onError4(Throwable e) {
                //todo error job fourth
            }

            @Override
            public void onStart() {
                //todo start from job first and complete job fourth
            }

            @Override
            public void onComplete() {
                //todo complete, if job first to fourth not exception catch error then call complete
            }
        });
    }
}
~~~

#### Implementation JobNetworkRxGlory
~~~java
class HomePresenter extends PresenterGlory<HomeDelegate> {
    public void requestUser{
       new JobNetworkRxGlory().subscribe(reqresEndpoint.singleUser(1), new OnJobNetworkGloryRx<ReqresUser>() {
            @Override
            public ReqresUser onJob(ReqresUser reqresUser) throws IOException {
                //todo job request
                return reqresUser;
            }

            @Override
            public void onSuccess(ReqresUser reqresUser) {
                //todo job success and update ui
            }

            @Override
            public void onError(Throwable e) {
                //todo error, if request endpoint error adapter, parser json and exception
            }

            @Override
            public void onErrorNetwork(String e) {
                //todo error, if request endpooint error http code, timeout and illegal exception
            }
        });
    }
}
~~~

#### Implementation JobNetworkMultipleRxGlory
~~~java
class HomePresenter extends PresenterGlory<HomeDelegate> {
    public void requestMultipleUser(){
          new JobNetworkMultipleRxGlory().subscribe(reqresEndpoint.singleUser(1), reqresEndpoint.singleUser(2), new OnJobNetworkMultipleGloryRx<ReqresUser, ReqresUser>() {
            @Override
            public ReqresUser onJob1(ReqresUser reqresUser) throws IOException {
                //todo job first
                return reqresUser;
            }

            @Override
            public void onSuccess1(ReqresUser reqresUser) {
                //todo success job second and update ui
            }

            @Override
            public void onError1(Throwable e) {
                //todo error job first, if request endpoint error adapter, parser json and exception
            }

            @Override
            public void onErrorNetwork1(String e) {
                //todo error job first, if request endpooint error http code, timeout and illegal exception
            }

            @Override
            public ReqresUser onJob2(ReqresUser reqresUser) throws IOException {
                //todo job second
                return reqresUser;
            }

            @Override
            public void onSuccess2(ReqresUser reqresUser) {
                //todo success job first and update ui
            }

            @Override
            public void onError2(Throwable e) {
                //todo error job second, if request endpoint error adapter, parser json and exception
            }

            @Override
            public void onErrorNetwork2(String e) {
                //todo error job second, if request endpooint error http code, timeout and illegal exception
            }
        });
    }
}
~~~



#### Implementation JobNetworkWithRealmRxGlory
~~~java
class HomePresenter extends PresenterGlory<HomeDelegate> {
    public void requestAndSave(){
          new JobNetworkWithRealmRxGlory().subscribe(reqresEndpoint.singleUser(1), new OnJobNetworkWithRealmGloryRx<ReqresUser>() {
            @Override
            public ReqresUser onJob(ReqresUser reqresUser, Realm realm) throws IOException {
                //todo job with realm
                return reqresUser;
            }

            @Override
            public void onSuccess(ReqresUser reqresUser) {
                //todo job success and update ui
            }

            @Override
            public void onError(Throwable e) {
                //todo error job second, if request endpoint error adapter, parser json and exception, or error realm exception
            }

            @Override
            public void onErrorNetwork(String e) {
                //todo error job second, if request endpooint error http code, timeout and illegal exception
            }
        });
    }
}
~~~

#### Implementation JobRealmRxGlory
~~~java
class HomePresenter extends PresenterGlory<HomeDelegate> {
    public void saveToDatabase(){
        new JobRealmRxGlory().subscribe(new OnJobRealmGloryRx<List<ResourceItem>>() {
            @Override
            public List<ResourceItem> onJob(Realm realm) throws IOException {
                //todo job realm
                return new ArrayList<>();
            }

            @Override
            public void onSuccess(List<ResourceItem> data) {
                //todo job success and update ui
            }

            @Override
            public void onError(Throwable e) {
                //todo job error exception realm
            }
        });
    }
}
~~~


## Custom Factory Header & Drawer
#### Custom Header Factory

#### Custom Drawer Factory

## Convention
#### Convention Package
com.yourapp.app
- endpoint 
  - yourendpointaEndpoint.java
  - yourendpointbEndpoint.java
  > package for interface endpoint
- di 
  > package for configuration depedency injection
- network
  - yourendpointa
    - request
    - response
  - yourendpointb
    - request
    - response
  > package for param request and response 
- database
  - realm
    - entity
    - repository
  - room
    - entity
    - repository
  > package for database entity and repository
* layer
    * youractivitya 
        * fragmenta 
        * fragmentb
    * youractivityb 
  > package for acivity dan fragment
  
#### Convention Endpoint in Gradle for Project

~~~gradle
def buildString = { k -> "\"${k}\"" }
android {
  buildTypes {

        staging {
            buildConfigField "String", "ENDPOINT_YOUR1", buildString("http://stagging.endpointyour1.com")
            buildConfigField "String", "ENDPOINT_YOUR1_TOKEN_AUTH", buildString("endpoint your1 token auth for stagging")
            buildConfigField "String", "ENDPOINT_YOUR1_TOKEN_PLATFORM", buildString("endpoint your1 token platform stagging")

            buildConfigField "String", "ENDPOINT_YOUR2", buildString("http://stagging.endpointyour2.com")
        }


        release {
            buildConfigField "String", "ENDPOINT_YOUR1", buildString("http://api.endpointyour1.com")
            buildConfigField "String", "ENDPOINT_YOUR1_TOKEN_AUTH", buildString("endpoint your1 token auth for release")
            buildConfigField "String", "ENDPOINT_YOUR1_TOKEN_PLATFORM", buildString("endpoint your1 token platform release")

            buildConfigField "String", "ENDPOINT_YOUR2", buildString("http://api.endpointyour2.com")

            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}


~~~
### GlorySDK included depedency gradle and built on : 
- Dagger
- RxJava
- Room
- Realm
- Retrofit
- Calligraphy

### Other Information
You can follow us at <https://rollingglory.com/>

