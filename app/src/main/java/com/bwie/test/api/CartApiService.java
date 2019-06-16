package com.bwie.test.api;

import com.bwie.test.entity.ProductEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

/**
 * Author:杨帅
 * Date:2019/6/15 9:12
 * Description：
 */
public interface CartApiService {
    @GET(Api.CART_URL)
    Observable<ProductEntity> checkCart(@HeaderMap Map<String, String> params);
}
