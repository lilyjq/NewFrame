package com.example.lily.newframe.di;

import com.example.lily.newframe.ui.home.HomeActivity;
import com.example.lily.newframe.ui.home.HomeMoudle;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ljq
 * on 2018/4/16.
 */
@Module
public abstract class ActivityBindingMoudle {


    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeMoudle.class)
    abstract HomeActivity homeActivity();



}
