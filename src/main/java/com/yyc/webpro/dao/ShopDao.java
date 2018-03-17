package com.yyc.webpro.dao;

import com.yyc.webpro.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 分页查询店铺，店铺名，店铺状态，店铺类别，区域，owener
     * @aram shopCondtion 查询条件i
     * @param rowIndex 从第几行开始读取
     * @param pageSize 返回的条数
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);


    /**
     * 返回shopList总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

}
