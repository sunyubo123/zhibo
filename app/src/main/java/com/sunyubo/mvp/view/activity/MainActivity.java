package com.sunyubo.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sunyubo.mvp.R;
import com.sunyubo.mvp.model.bean.Api;
import com.sunyubo.mvp.model.bean.CustomObserver;
import com.sunyubo.mvp.model.bean.HttpResult;
import com.sunyubo.mvp.model.bean.Joker;
import com.sunyubo.mvp.model.bean.RxHelper;
import java.util.List;
import io.reactivex.Observable;
/**
*1.类的作用
*2.Sunyubo
*3.2017/2/21
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<HttpResult<List<Joker>>> observable=Api.getRetrofit().getJokerData("");
       /* observable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult<List<Joker>>>() {
                    @Override
                    public void accept(HttpResult<List<Joker>> listHttpResult) throws Exception {
                 //       Log.d("TAG","jokers:"+listHttpResult.);
                    }
                });*/
        RxHelper.toSubscriber(observable, new CustomObserver<List<Joker>>(this) {
            @Override
            public void onSuccess(List<Joker> jokers) {
                Log.e("TAG","jokers:"+jokers.get(1).getContent());
            }
        });
    }
}
