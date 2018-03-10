package com.yyc.webpro.dto;

import com.yyc.webpro.entity.Shop;
import com.yyc.webpro.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
    //结果
    private int state;

    //状态标识
    private String stateinfo;

    //店铺数量
    private int count;

    //操作的店铺(增删改查)
    private Shop shop;

    //shop列表
    private List<Shop> shopList;

    public ShopExecution() {

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * 店铺操作失败的构造器
     * @param stateEnum
     */
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateinfo = stateEnum.getStateInfo();
    }

    /**
     * 店铺操作成功的构造器
     * @param stateEnum
     */
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateinfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    /**
     * 店铺操作成功的构造器
     * @param stateEnum, shopList
     */
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateinfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
