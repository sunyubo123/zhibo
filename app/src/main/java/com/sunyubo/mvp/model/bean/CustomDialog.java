package com.sunyubo.mvp.model.bean;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.sunyubo.mvp.R;

/**
 * 1.类的用途
 * 2.@author:Sunyubo
 * 3.@ 2017/2/21.
 */

public class CustomDialog {
    private Context context;
    private Dialog dialog;
    private static  CustomDialog customDialog;
    DialogCancelLisenter lisenter;
    private CustomDialog(Context context,DialogCancelLisenter dialogCancelLisenter){
        this.context=context;
this.lisenter=dialogCancelLisenter;
    }
    public void initDialog(){
        dialog=new Dialog(context);
        View view= LayoutInflater.from(context).inflate(R.layout.custom_dialog,null);
        dialog.setCancelable(true); //点击返回按钮是否让dialog消失
        dialog.setCanceledOnTouchOutside(false); //点击外部是否消失
        dialog.setContentView(view);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                //回调Dialog消失的方法
                if(lisenter != null){
                    lisenter.onCancel();
                }
            }
        });
    }
    public  static  CustomDialog getInstance(Context context,DialogCancelLisenter lisenter){
        if(customDialog==null){
            synchronized (CustomDialog.class){
                if(customDialog==null){
                    customDialog=new CustomDialog(context,lisenter);
                }
            }
        }
        return  customDialog;
    }
    public interface DialogCancelLisenter{
        void onCancel();
    }

    public void show(){
        if(dialog != null && !dialog .isShowing()){
            dialog.show();
        }
    }

    public void dissmiss(){
        if(dialog != null && dialog .isShowing()){
            dialog.dismiss();
        }
    }
}

