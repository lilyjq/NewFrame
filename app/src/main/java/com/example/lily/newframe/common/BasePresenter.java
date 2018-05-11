package com.example.lily.newframe.common;

/**
 * Created by ljq
 * on 2018/4/9.
 */

public interface BasePresenter <T>{

    void bindView(T View);
    void unbindView();
}
