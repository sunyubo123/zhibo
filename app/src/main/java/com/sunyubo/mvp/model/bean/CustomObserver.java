package com.sunyubo.mvp.model.bean;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.JsonSyntaxException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/**
 * Created by sunyubo on 2017/2/21.
 */

public abstract class CustomObserver<T> implements Observer<T>,CustomDialog.DialogCancelLisenter {

    private final CustomDialog mDialog;
    private Context mContext;
    private boolean flag = true;


    public CustomObserver(Context context) {
        this.mContext = context;
        mDialog = CustomDialog.getInstance(context, this);
    }
    public CustomObserver(Context context,boolean flag) {
        this.flag = flag;
        this.mContext = context;
        mDialog = CustomDialog.getInstance(context, this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(mDialog != null){
            if(flag){
                mDialog.show();

            }
        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
        } else if ((e instanceof UnknownHostException)) {
            Toast.makeText(mContext,"网络异常",Toast.LENGTH_SHORT).show();
        } else if (e instanceof JsonSyntaxException) {
            Toast.makeText(mContext,"数据异常",Toast.LENGTH_SHORT).show();
        } else if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext,"连接超时",Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext, "连接服务器失败", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext,"未知错误",Toast.LENGTH_SHORT).show();
        }
        Log.d("TAG","e:"+e);
        if(mDialog != null){
            mDialog.dissmiss();
        }
    }

    @Override
    public void onComplete() {
        if(mDialog != null){
            mDialog.dissmiss();
        }
    }

    @Override
    public void onCancel() {

    }
    public abstract void onSuccess(T t);

}
