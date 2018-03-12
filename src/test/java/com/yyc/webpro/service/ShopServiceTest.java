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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        InputStream shopImg = null;
        String fileName = "G:\\Documents\\Personal\\Pic\\MavenProPic\\2b.jpg";
        try {
            shopImg = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ShopExecution se = shopService.addShop(shop, shopImg, fileName);

        assert ShopStateEnum.CHECK.getState() == se.getState();
    }
}
