package com.yf.sockethttp.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
    private static final String TAG = "HttpUtil-1";
    private static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static InputStream inputStream;
    private static URLConnection urlConnection;

    public interface HttpListener {
        void onSuccess(String content);

        void onFail(Exception ex);
    }

    public static void doGet(final String urll, final HttpListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urll);
                    urlConnection = url.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setReadTimeout(5000);
                    urlConnection.connect();
                    inputStream = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    char[] buf = new char[2048];
                    int len = -1;
                    final StringBuilder content = new StringBuilder();
                    while ((len = reader.read(buf)) != -1) {
                        content.append(new String(buf, 0, len));

                    }
                    Log.d(TAG, "run:1234 "+content.toString());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess(content.toString());
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    listener.onFail(e);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(inputStream!=null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
}
