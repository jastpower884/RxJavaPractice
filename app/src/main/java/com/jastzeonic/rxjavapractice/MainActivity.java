package com.jastzeonic.rxjavapractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建一个观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.i(TAG, "Completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Error");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }
        };
        //使用Observable.create()创建被观察者
        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Wrold");
                subscriber.onNext("observable 1");
                subscriber.onCompleted();
            }
        });
        //订阅
        observable1.subscribe(observer);


        Observable observable2 = Observable.just("Hello observable 2", "World");
        observable2.subscribe(observer);
        String[] words = {"Hello", "World", "observable3"};
        Observable observable3 = Observable.from(words);
        observable3.subscribe(observer);

    }
}
