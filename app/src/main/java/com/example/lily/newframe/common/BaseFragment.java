package com.example.lily.newframe.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ljq
 * on 2018/4/8.
 */

public abstract class BaseFragment <T extends BasePresenter>extends Fragment implements BaseView<T>{

    protected  abstract  int getContentView();
    protected  abstract  void handleBundle(Bundle bundle);
    protected  abstract  void initVairables();
    protected  abstract  int getLayoutRes();
    protected  abstract  void initViews(View view);
    protected  abstract  void initListener();
    protected   T presenter;
    protected  Context context;
    protected  BaseDialogFragmentImp imp;

  private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVairables();
        handleArguments();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(inflater==null){
            return  null;
       }
        View view=inflater.inflate(getLayoutRes(),container,false);
        unbinder=ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view==null){
            return;
        }
        initViews(view);
        initListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.cancelRequest();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void handleArguments(){
        Bundle bundle=getArguments();
        if(bundle!=null){
            handleBundle(bundle);
        }

    }

    @Override
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    protected  void showMsgShort(String msg){
        if(isActive()){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }

    protected  void showMsgLong(String msg){
        if(isActive()){
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showLoadingDailog(String msg) {
        if(isActive()) {
            if (imp == null) {
                imp = BaseDialogFragmentImp.newInstance(msg);
                imp.show(getFragmentManager(), "tag");
            }
        }

    }

    @Override
    public void hideLoadingDailog() {
       if(!isActive()){
           return;
       }

       if(imp!=null){
           imp.dismiss();
       }
    }
}
