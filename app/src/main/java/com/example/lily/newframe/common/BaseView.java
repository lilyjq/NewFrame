package com.example.lily.newframe.common;

/**
 * Created by ljq
 * on 2018/4/9.
 */

public interface BaseView <T>{

    /**
     * 让view层持有presenter的引用
     * @param presenter
     */
  void setPresenter(T presenter);

    /**
     * 如果是fragment 看是否该fragment是否依附在activity上
     * activity 直接返回true
     * @return
     */
  boolean isActive();

    /**
     * 播放加载动画
     */
    void showLoadingDailog(String msg);

    /**
     * 关闭加载动画
     */
    void hideLoadingDailog();





}
