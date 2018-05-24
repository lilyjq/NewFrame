package com.example.lily.newframe.ui.home;


import com.example.lily.newframe.common.CallBackListener;
import com.example.lily.newframe.common.HttpManager;
import com.example.lily.newframe.common.TaskApi;
import com.example.lily.newframe.entity.UserInfo;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ljq
 * on 2018/5/7.
 */

public class HomePresenter implements HomeContract.HomePresenter  {

    private HttpManager httpManager;
    private TaskApi taskApi;
    private CompositeDisposable compositeDisposable;
    private HomeContract.HomeView homeView;


    @Inject
    HomePresenter(HttpManager httpManager, TaskApi taskApi){
        this.httpManager = httpManager;
        this.taskApi = taskApi;
        compositeDisposable = new CompositeDisposable();

    }



    @Override
    public void bindView(HomeContract.HomeView View) {
        homeView = View;
    }

    @Override
    public void unbindView() {

        String key="";
        String uername="";
        httpManager.requset(taskApi.LoginIn(key,uername), compositeDisposable, homeView, new CallBackListener<UserInfo>() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onSuccess(UserInfo data) {

            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }
}
