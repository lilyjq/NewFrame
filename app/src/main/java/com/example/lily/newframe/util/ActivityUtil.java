package com.example.lily.newframe.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by ljq
 * on 2018/5/8.
 */

public class ActivityUtil {


    public static void addFragement(FragmentManager fragmentManager, Fragment fragment,int LayoutRes){

        if(fragmentManager == null || fragment == null || fragment.isAdded() ){
            return;
        }
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(LayoutRes,fragment);
        transaction.commit();
    }
}
