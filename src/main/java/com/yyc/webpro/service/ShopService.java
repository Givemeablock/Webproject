package com.yyc.webpro.service;

import com.yyc.webpro.dto.ShopExecution;
import com.yyc.webpro.entity.Shop;

import java.io.File;

public interface ShopService {

    ShopExecution addShop(Shop shop, File shopImg);

}
