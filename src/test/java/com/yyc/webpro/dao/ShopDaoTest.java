package com.yyc.webpro.dao;

import com.yyc.webpro.BasicTest;
import com.yyc.webpro.entity.Area;
import com.yyc.webpro.entity.PersonInfo;
import com.yyc.webpro.entity.Shop;
import com.yyc.webpro.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ShopDaoTest extends BasicTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    //这将不会执行该测试用例
    @Ignore
     public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        personInfo.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(personInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("test店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");

        shopDao.insertShop(shop);

    }

    @Test
    @Ignore
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("test更改");
        shop.setUpdateTime(new Date());

        int effectNum = shopDao.updateShop(shop);
        assert 1 == effectNum;

    }

    @Test
    public void testqueryShopByShopId() {
        long shopId = 13L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId" + shop.getArea().getAreaId());
        System.out.println("areaName" + shop.getArea().getAreaName());
       // System.out.println("owner" + shop.getOwner().getUserName());
    }


    @Test
    public void testqueryShopListAndCount() {
        Shop shopCondition = new Shop();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        shopCondition.setOwner(personInfo);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(2L);
        shopCondition.setShopCategory(shopCategory);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("count:" + count);
    }
}
