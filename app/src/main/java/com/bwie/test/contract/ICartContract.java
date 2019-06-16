package com.bwie.test.contract;

import android.content.Context;

import com.bwie.test.entity.ProductEntity;
import com.bwie.test.net.NetCallBack;

import java.util.Map;

/**
 * Author:杨帅
 * Date:2019/6/15 9:14
 * Description：
 */
public interface ICartContract {
    interface ICartModel{
        void requestHttpData(Map<String,String> params, Context context, NetCallBack callBack);
    }
    interface ICartView{
        void showPreData(ProductEntity productEntity);
        void showError(String msg);
    }
    interface ICartPresneter{
        void requestModelData(Map<String,String> params, Context context);
    }
}
