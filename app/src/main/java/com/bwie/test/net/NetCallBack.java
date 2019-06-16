package com.bwie.test.net;

import com.bwie.test.entity.ProductEntity;

/**
 * Author:杨帅
 * Date:2019/6/15 9:16
 * Description：
 */
public interface NetCallBack {
    void onSuccess(ProductEntity productEntity);
    void onFailure(String msg);
}
