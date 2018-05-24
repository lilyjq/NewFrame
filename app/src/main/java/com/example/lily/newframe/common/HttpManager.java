package com.example.lily.newframe.common;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ljq
 * on 2018/5/8.
 */

public class HttpManager {



    public <T> void requset(Observable<Response<T>> observable, final CompositeDisposable compositeDisposable,
                            final BaseView baseView, final  CallBackListener<T> listener){


        if(observable == null || compositeDisposable == null || baseView == null || listener == null){
            return;
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<T> Response) {
                        if(Response != null){

                        }

                    }


                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
