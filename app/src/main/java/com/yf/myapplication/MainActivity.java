package com.yf.myapplication;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity-1";
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                Log.d(TAG, "上游发射------->a");
                emitter.onNext("发射------>a");
                Log.d(TAG, "上游发射-------->b");
                emitter.onNext("发射-------->b");
                Log.d(TAG, "上游发射------->c");
                emitter.onNext("发射------>c");
                Log.d(TAG, "上游发射-------->d");
                emitter.onNext("发射-------->d");
            }
        });

        Observer observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
                disposable = d;
            }

            @Override
            public void onNext(String o) {
                if (o.equals("发射-------->b")) {
                    disposable.dispose();

                }
                Log.d(TAG, "onNext: 下游next接收"+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        observable.subscribe(observer);
    }


}
