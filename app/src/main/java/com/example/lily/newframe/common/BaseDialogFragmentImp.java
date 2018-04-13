package com.example.lily.newframe.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.lily.newframe.R;

/**
 * Created by ljq
 * on 2018/4/9.
 */

public class BaseDialogFragmentImp extends BaseDialogFragment {
    String msg;


    @Override
    protected void handleBundle(Bundle bundle) {
          msg=bundle.getString("msg");
    }

    @Override
    protected Dialog createDailog() {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_basedialog,null);
        TextView tv=view.findViewById(R.id.dialog_msg);
        tv.setText(msg);
        Dialog dialog =new Dialog(context,R.style.commonProgressDailog);
        dialog.setContentView(view);
        return dialog;
    }



    public static BaseDialogFragmentImp newInstance(String msg){
        BaseDialogFragmentImp imp=new BaseDialogFragmentImp();
        Bundle bundle=new Bundle();
        bundle.putString("msg",msg);
        imp.setArguments(bundle);
        return  imp;
    }

}
