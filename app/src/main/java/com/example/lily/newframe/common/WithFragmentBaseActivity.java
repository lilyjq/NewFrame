package com.example.lily.newframe.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.example.lily.newframe.R;

import butterknife.ButterKnife;

/**
 * Created by ljq
 * on 2018/4/8.
 */

public abstract class WithFragmentBaseActivity extends AppCompatActivity {

    protected abstract int getContentView();

    protected abstract void handleBundle(Bundle bundle);

    protected abstract void initListener();

    protected abstract void onNavigationIcoClick(View v);


    private boolean defToolBar;//是否显示toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        handleIntent();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 跳转aty
     *
     * @param cls 要跳转的aty
     */
    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 带requestCode的跳转
     *
     * @param cls         cls
     * @param requestCode requestCode
     */
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                handleBundle(bundle);
            }
        }
    }


    public void setDefToolBar(boolean isdefault) {
        defToolBar = isdefault;
    }


    private void initToolbar() {
        if (defToolBar) {
            toolbar = findViewById(R.id.commmon_toolbar);
        }
    }

    protected void setToolbarTitle(String str) {
        if (toolbar != null) {
            toolbar.setTitle(str);
            setSupportActionBar(toolbar);
        }
    }

    protected void setToolbarTitle(int res) {
        if (toolbar != null) {
            toolbar.setTitle(res);
        }
    }


    protected void setDispalyomeAsEnabled(boolean enabled) {
        if (toolbar != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(enabled);
                if (enabled) {
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onNavigationIcoClick(v);
                        }
                    });
                }
            }
        }
    }


    protected  void setNaviagtionIco(int Res){
        if(toolbar!=null){
            toolbar.setNavigationIcon(Res);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationIcoClick(v);
                }
            });
        }


    }
    protected void showMsgShort(String msg) {
        if (isFinishing()) {
            return;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showMsgLong(String msg) {
        if (isFinishing()) {
            return;
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
