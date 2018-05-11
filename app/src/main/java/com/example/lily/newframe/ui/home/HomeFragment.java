package com.example.lily.newframe.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.lily.newframe.R;
import com.example.lily.newframe.common.BaseFragment;

import javax.inject.Inject;

/**
 * Created by ljq
 * on 2018/5/7.
 */

public class HomeFragment extends BaseFragment {

    @SuppressLint("ValidFragment")
    @Inject
    HomeFragment(){

    }


    @Inject
    HomeContract.HomePresenter presenter;




    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void initVairables() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initListener() {

    }
}
