package com.example.lily.newframe.ui.home;

import com.example.lily.newframe.di.ActivityScoped;
import com.example.lily.newframe.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ljq
 * on 2018/5/7.
 */

@Module
public abstract class HomeMoudle {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract  HomeFragment homeFragment();

    @ActivityScoped
    @Binds abstract  HomeContract.HomePresenter homepresenter(HomePresenter presenter);

}
