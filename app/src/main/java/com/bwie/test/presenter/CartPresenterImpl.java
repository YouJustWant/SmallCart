package com.bwie.test.presenter;

import android.content.Context;

import com.bwie.test.contract.ICartContract;
import com.bwie.test.entity.ProductEntity;
import com.bwie.test.model.CartModelImpl;
import com.bwie.test.net.NetCallBack;

import java.util.Map;

/**
 * Author:杨帅
 * Date:2019/6/15 9:27
 * Description：
 */
public class CartPresenterImpl implements ICartContract.ICartPresneter {
    private ICartContract.ICartView cartView;
    private ICartContract.ICartModel model;

    public CartPresenterImpl(ICartContract.ICartView cartView) {
        this.cartView = cartView;
        model = new CartModelImpl();
    }

    @Override
    public void requestModelData(Map<String, String> params, Context context) {
        model.requestHttpData(params, context, new NetCallBack() {
            @Override
            public void onSuccess(ProductEntity productEntity) {
                if (productEntity != null) {
                    cartView.showPreData(productEntity);
                }
            }

            @Override
            public void onFailure(String msg) {
                cartView.showError(msg);
            }
        });
    }

    /**
     * 解绑防止内存泄露
     */
    public void detach() {
        if (cartView != null) {
            cartView = null;
        }
        if (model != null) {
            model = null;
        }
        System.gc();
    }
}
