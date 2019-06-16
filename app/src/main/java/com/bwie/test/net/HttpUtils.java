package com.bwie.test.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bwie.test.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:杨帅
 * Date:2019/6/15 9:17
 * Description：
 */
public class HttpUtils {
    private static HttpUtils httpUtils;
    private final Retrofit retrofit;

    private HttpUtils(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .build();
    }
    public static HttpUtils getInstance(){
        if(httpUtils==null){
            synchronized (HttpUtils.class){
                if(httpUtils==null){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    /**
     * 判断网络状态
     * @param context
     * @return
     */
    public boolean isNetWork(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);//获取连接管理者
        NetworkInfo info = manager.getActiveNetworkInfo();//获取连接状态信息
        if(info!=null){//判断状态
            return info.isConnected();
        }
        return false;
    }

    /**
     * 动态代理生成ApiService
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
