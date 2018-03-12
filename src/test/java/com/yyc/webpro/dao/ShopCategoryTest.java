package com.yyc.webpro.dao;

import com.yyc.webpro.BasicTest;
import com.yyc.webpro.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryTest extends BasicTest{

    @Autowired
    private ShopCategoryDao shopCategoryDao;
    //shopcategory相关dao test service controller在上次提交时候误提交了

    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
    }
}
