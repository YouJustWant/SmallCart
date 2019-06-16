package com.bwie.test.entity;

import java.util.List;

/**
 * Author:杨帅
 * Date:2019/6/15 9:07
 * Description：
 */
public class ProductEntity {
    public String message;
    public String status;
    public List<Product> result;

    public class Product {
        public String categoryName;
        public List<Goods> shoppingCartList;
    }

    public class Goods {
        public boolean itemFlag;
        public String commodityId;
        public String commodityName;
        public String count;
        public String pic;
        public String price;
        public String proNum ="1";
    }
}
