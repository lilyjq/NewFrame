package com.example.lily.newframe.ui.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.lily.newframe.R;
import com.example.lily.newframe.common.BaseActivity;
import com.example.lily.newframe.util.ActivityUtil;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    HomePresenter presenter;

    @Inject
    HomeFragment injectfragment;

    FragmentManager manager;

    RecyclerView recyclerView;
    HomeSideAdapter adapter;
    Toolbar toolbar;
    DrawerLayout drawerLayout;




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
        drawerLayout = findViewById(R.id.drawerlayout);

        toolbar = findViewById(R.id.side_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(this);
        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.abc_ic_menu_moreoverflow_mtrl_alpha));

        recyclerView = findViewById(R.id.homeleft_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeSideAdapter(this);
        recyclerView.setAdapter(adapter);

        HomeFragment  fragment = (HomeFragment) manager.findFragmentById(R.id.commmon_content);
        if(fragment == null){
            fragment = injectfragment;
            ActivityUtil.addFragement(manager,fragment,R.id.commmon_content);
        }
    }


    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    protected void initVairable() {
        manager = getSupportFragmentManager();
    }


    @Override
    public void onClick(View v) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            drawerLayout.openDrawer(GravityCompat.START);
        }

    }


}
