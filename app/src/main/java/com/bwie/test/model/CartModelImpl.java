package com.bwie.test.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bwie.test.api.Api;
import com.bwie.test.api.CartApiService;
import com.bwie.test.contract.ICartContract;
import com.bwie.test.entity.ProductEntity;
import com.bwie.test.net.HttpUtils;
import com.bwie.test.net.NetCallBack;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

/**
 * Author:杨帅
 * Date:2019/6/15 9:24
 * Description：
 */
public class CartModelImpl implements ICartContract.ICartModel {
    private HttpUtils httpUtils;

    public CartModelImpl() {
        httpUtils = HttpUtils.getInstance();
    }

    @SuppressLint("CheckResult")
    @Override
    public void requestHttpData(Map<String, String> params, Context context, NetCallBack callBack) {
        if (httpUtils.isNetWork(context)) {
            CartApiService service = httpUtils.createService(CartApiService.class);
            service.checkCart(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ProductEntity>() {
                @Override
                public void accept(ProductEntity productEntity) throws Exception {
                    if (callBack != null) {
                        callBack.onSuccess(productEntity);
                    }

                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    if (callBack != null) {
                        callBack.onFailure("网络异常，请稍后再试");
                    }
                    Log.d(TAG, "accept: " + throwable.toString());
                }
            });
        } else {
            Toast.makeText(context, "没有网络", Toast.LENGTH_SHORT).show();
        }
    }
}
