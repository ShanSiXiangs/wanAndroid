package com.sx.wanandroid.NetWork;

import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper;
    private Retrofit mRetrofit;

    //实例化本类
    public static RetrofitHelper Instance(){
        if (retrofitHelper == null){
            retrofitHelper = new RetrofitHelper();
        }
        return retrofitHelper;
    }
    //构造方法
    private RetrofitHelper(){
        InitRetrofit();
    }
    //初始化 Retrofit
    private void InitRetrofit(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000L, TimeUnit.MILLISECONDS)
                .readTimeout(1000L, TimeUnit.MILLISECONDS)
                .writeTimeout(1000L, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)//添加日志拦截器
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    //初始化 RetrofitService
    public RetrofitService getRetrofitService(){
       return mRetrofit.create(RetrofitService.class);
    }

}
