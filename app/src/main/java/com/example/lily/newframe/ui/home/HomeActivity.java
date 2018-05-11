package com.example.lily.newframe.ui.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.lily.newframe.R;
import com.example.lily.newframe.common.BaseActivity;
import com.example.lily.newframe.util.ActivityUtil;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {

    @Inject
    HomePresenter presenter;

    @Inject
    HomeFragment injectfragment;

    FragmentManager manager;

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void handleBundle(Bundle bundle) {


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onNavigationIcoClick(View v) {

    }

    @Override
    protected void initView() {
        HomeFragment  fragment = (HomeFragment) manager.findFragmentById(R.id.commmon_content);
        if(fragment == null){
            fragment = injectfragment;
            ActivityUtil.addFragement(manager,fragment,R.id.commmon_content);
        }
    }

    @Override
    protected void initVairable() {
        manager = getSupportFragmentManager();
    }


}
