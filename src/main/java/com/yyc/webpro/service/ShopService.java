package com.yyc.webpro.service;

import com.yyc.webpro.dto.ShopExecution;
import com.yyc.webpro.entity.Shop;
import com.yyc.webpro.exception.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {

    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    Shop getByShopId(long shopId);
}
