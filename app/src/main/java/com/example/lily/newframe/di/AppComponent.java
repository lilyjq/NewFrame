package com.example.lily.newframe.di;

import android.app.Application;
import android.content.Context;

import com.example.lily.newframe.common.AppApplication;
import com.example.lily.newframe.common.CallBackListener;
import com.example.lily.newframe.common.HttpManager;
import com.example.lily.newframe.common.TaskApi;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;

/**
 * Created by ljq
 * on 2018/4/16.
 */
@Singleton
@Component(modules = {AppMoudle.class,
        ActivityBindingMoudle.class,
        ActivityTaskMoudle.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<AppApplication>{




    TaskApi getTaskApi();

    HttpManager getHttpManager();


    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder{
        @BindsInstance
        AppComponent.Builder application(Application application);
        AppComponent build();


        AppComponent.Builder  TaskMoudle(ActivityTaskMoudle activityTaskMoudle);

    }



}
