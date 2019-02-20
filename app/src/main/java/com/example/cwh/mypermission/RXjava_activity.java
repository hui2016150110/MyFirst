package com.example.cwh.mypermission;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Action1;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;

public class RXjava_activity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_layout);
        imageView =(ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        
        
        /*简单的案例
        rx.Observer<String> observer = new rx.Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("info","completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("info","onError");
            }

            @Override
            public void onNext(String s) {
                Log.i("info","s");
            }
        };
        
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("rxjava");
                subscriber.onCompleted();
            }
        });
        Observable<String> observable = Observable.just("1","2","3");
        Observable<String> observable = Observable.from(new String[]{"1","2"});
        observable.subscribe(observer);*/
       
    }

    @Override
    public void onClick(View v) {

        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void subscribe(ObservableEmitter<Drawable> e) throws Exception {
                Drawable drawable = getTheme().getDrawable(R.drawable.fruit);
                e.onNext(drawable);
                e.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())//指定 subscribe() 发生在 IO 线程
        .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
        .subscribe(new Observer<Drawable>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        });

        Observable.just("")
        .map(new Function<String, Bitmap>() {
            @Override
            public Bitmap apply(String s) throws Exception {
                return getBitmapFromPath(s);
            }

        })
        .subscribe(new Consumer<Bitmap>() {
            @Override
            public void accept(Bitmap bitmap) throws Exception {
                showBitmap(bitmap);
            }
        });




    }

    private void showBitmap(Bitmap bitmap) {
    }
    private Bitmap getBitmapFromPath(String s) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fruit);
        //File file = new File("");
        return bitmap;
    }
}
