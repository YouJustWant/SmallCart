package com.bwie.test.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:杨帅
 * Date:2019/6/15 9:05
 * Description：
 */
@Entity
public class CartEntity {
    private Long id;
    private String json;
    @Generated(hash = 1371102896)
    public CartEntity(Long id, String json) {
        this.id = id;
        this.json = json;
    }
    @Generated(hash = 1508478210)
    public CartEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }
}
