package com.yyc.webpro.dao;

import com.yyc.webpro.entity.Shop;

public interface ShopDao {
    /**
     * 新增店铺
     *
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    int updateShop(Shop shop);

    Shop queryByShopId(long shopId);
}
