package com.yyc.webpro.service;

import com.yyc.webpro.BasicTest;
import com.yyc.webpro.dto.ShopExecution;
import com.yyc.webpro.entity.Area;
import com.yyc.webpro.entity.PersonInfo;
import com.yyc.webpro.entity.Shop;
import com.yyc.webpro.entity.ShopCategory;
import com.yyc.webpro.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

public class ShopServiceTest extends BasicTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() {
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
        shop.setShopName("test店铺22");
        shop.setShopDesc("test22");
        shop.setShopAddr("test22");
        shop.setPhone("test22");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("G:\\Documents\\Personal\\Pic\\MavenProPic\\test2.jpg");

        ShopExecution se = shopService.addShop(shop, shopImg);

        assert ShopStateEnum.CHECK.getState() == se.getState();
    }
}
