package com.example.lily.newframe.common;







import com.example.lily.newframe.di.ActivityTaskMoudle;
import com.example.lily.newframe.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by ljq
 * on 2018/4/16.
 */

public class AppApplication extends DaggerApplication{


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
       return DaggerAppComponent.builder().application(this).TaskMoudle(new ActivityTaskMoudle(this)).build();


  //   return  null;
    }



}
