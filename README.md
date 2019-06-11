
## Rolling Glory 
Rollingglory is Company or Creative Digital Media studio based in Bandung, Indonesia.

## GlorySDK
GlorySDK is bundle API for Utility, Network, UI, Component and Architecture.

## Quick Start 

#### Gradle Depedency

~~~gradle
repositories {
    maven {
        url  "https://dl.bintray.com/rollingglory/GlorySDK-android"
    }
}

dependencies {
    implementation 'com.rollingglory:glorysdk-android:0.0.1-alpha01'
}
~~~


#### Maven Depedency

~~~gradle
<dependency>
    <groupId>com.rollingglory</groupId>
    <artifactId>glorysdk-android</artifactId>
    <version>0.0.1-alpha01</version>
    <type>pom</type>
</dependency>
~~~

#### Gradle Configuration App
~~~gradle
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'
apply plugin: 'realm-android'

repositories {
    maven { url 'https://maven.fabric.io/public' }
}
def buildString = { k -> "\"${k}\"" }

android {

    buildTypes {
     debug {
            buildConfigField "String", "ENDPOINT_DEFAULT", buildString("http://")
            buildConfigField "String", "ENDPOINT_DEFAULT_TOKEN_AUTH", buildString("TOKEN")
            buildConfigField "String", "ENDPOINT_DEFAULT_TOKEN_PLATFORM", buildString("TOKEN")

            buildConfigField "String", "ENDPOINT_TWITTER", buildString("http://")
        }

        staging {
            buildConfigField "String", "ENDPOINT_DEFAULT", buildString("http://")
            buildConfigField "String", "ENDPOINT_DEFAULT_TOKEN_AUTH", buildString("TOKEN")
            buildConfigField "String", "ENDPOINT_DEFAULT_TOKEN_PLATFORM", buildString("TOKEN")

            buildConfigField "String", "ENDPOINT_TWITTER", buildString("http://")
        }


        release {
            buildConfigField "String", "ENDPOINT_DEFAULT", buildString("http://")
            buildConfigField "String", "ENDPOINT_DEFAULT_TOKEN_AUTH", buildString("TOKEN")
            buildConfigField "String", "ENDPOINT_DEFAULT_TOKEN_PLATFORM", buildString("TOKEN")

            buildConfigField "String", "ENDPOINT_TWITTER", buildString("http://")

            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }


    }
    dataBinding {
        enabled = true
    }
}
~~~

#### Gradle Configuration Project
~~~gradle
buildscript {
    ext.kotlin_version = 'x.x.xx'
    repositories {
        google()
        jcenter()
    }
    
    classpath 'com.android.tools.build:gradle:3.4.1'
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    classpath "io.realm:realm-gradle-plugin:x.xx.x"
    
    allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://maven.fabric.io/public' }
    }
}

~~~

#### Gradle Properties
~~~gradle
android.useAndroidX=true
android.databinding.enableV2=true
android.enableJetifier=true
~~~
## Base GlorySDK
#### Arcithecture GlorySDK
| Class | Migration Of | Role |
| ------ | ------ | ------ |
|ApplicationGlory | Application | Application |
| ActivityGlory<t1 extends HeaderGlory.Factory,t2 extends ViewDataBinding,t3 extends PresenterGlory> | AppCompatActivity | Activity
| PresenterGlory<t1 extends PresenterDelegateGlory> | - | Presenter|
| PresenterDelegateGlory | - | Presenter Delegate |
| ActivityModuleGlory<t1 extends HeaderGlory.Factory,t2 extends ViewDataBinding, t3 extends PresenterGlory,t4 extends ActivityGlory<t1,t2,t3>> | - | Provided Injection |


#### Depedency Injection Support AndroidX
| Class | Migration Of | Role |
| ------ | ------ | ------ |
| AndroidInjectionX | AndroidInjection | Injection
| HasActivityInjectorX | HasActivityInjector | Mark Activity Has Inject|
| HasFragmentInjectorX | HasFragmentInjector | Mark Fragment Has Inject |
| HasPresenterInjectorX | - | Mark Presenter Has Inject |


#### Header Factory
Default Header factory **ToolbarHeader**,**TabHeader**,**BannerHeader** extends of **HeaderGlory.Factory**


## Convention
#### Convention Package
com.sample.app
- endpoint 
  > use for interface endpoint
- di 
  >use for configuration depedency injection
- network
  - request
  - response
  > use for param request and response 
- database
  - realm
  - room
  > use for database entity and repository
- layer
  > use for layer acivity dan fragment



## Style
- ThemeGlory
- ThemeGlory.NoActionBar
- ThemeGlory.NoActionAndStatusBar
- ThemeGlory.AppBarOverlay

## Component
*Comming Soon*
## Database
*Comming Soon*
## Utils
*Comming Soon*
## Unit Test
*Comming Soon*

## Quick Implementation
## Architecture MVP
#### Activity
~~~java
class HomeActivity extends ActivityGlory<HomeHeader,ActivityHomeBinding,HomePresenter> implements HomeDelegate{

    @Inject HomePresenter homePresenter;
    @Inject ActivityHomeBinding binding;
    @Inject HomeHeader header;
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
    }
}
~~~

#### Presenter
~~~java
 class HomePresenter extends PresenterGlory<HomeDelegate> {

    @Inject
    DefaultEndpoint defaultEndpoint;

    @Inject
    YtsEndpoint ytsEndpoint;

    void requestCategory(){

        defaultEndpoint.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    getPresenterDelegate().sizeCategory(response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }
}
~~~


#### Presenter Delegate
~~~java
interface HomeDelegate extends PresenterDelegateGlory {
   
}
~~~


#### Module Provided
~~~java
@Module
class HomeModule extends ActivityModuleGlory<HomeHeader,ActivityHomeBinding,HomePresenter,HomeActivity>

    @Module
    public abstract class Bind{

    }
}
~~~

#### Custom Header Factory

~~~java
//HomeHeader.java
class HomeHeader extends HeaderGlory.Factory {
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
~~~

~~~xml
 <!--header_factory_home.xml-->
<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.appbar.AppBarLayout android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:theme="@style/ThemeGlory.AppBarOverlay"

    xmlns:android="http://schemas.android.com/apk/res/android">

    <ImageView
        android:id="@+id/image"
        android:layout_width="40dp"
        android:layout_height="40dp" />
    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?actionBarSize"
        android:background="?attr/colorPrimary"
        app:popupTheme="@style/ThemeGlory.PopupOverlay" />

</com.google.android.material.appbar.AppBarLayout>
~~~

~~~java
 //HomeActivity.java
class HomeActivity extends ActivityGlory<HomeHeader,ActivityHomeBinding,HomePresenter> implements HomeDelegate{


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
}

~~~

### Other Information
You can follow us at <https://rollingglory.com/>

