package com.yyc.webpro.service.impl;

import com.yyc.webpro.dao.ShopDao;
import com.yyc.webpro.dto.ShopExecution;
import com.yyc.webpro.entity.Shop;
import com.yyc.webpro.enums.ShopStateEnum;
import com.yyc.webpro.exception.ShopOperationException;
import com.yyc.webpro.service.ShopService;
import com.yyc.webpro.util.ImageUtil;
import com.yyc.webpro.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException{

        //判断shop是不是空值
        if (shop==null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOPINFO);
        }
        try{
            //给店铺赋值初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setUpdateTime(new Date());
            int effectNum = shopDao.insertShop(shop);
            if (effectNum <= 0){
                //添加失败
                throw new ShopOperationException("店铺创建失败");
            }
            else{
                //添加成功
                if (shopImgInputStream != null) {
                    //存储图片
                    try {

                    }
                    catch (Exception e) {
                        throw new ShopOperationException("添加店铺图片失败" + e.getMessage());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);

                    //更新店铺图片
                    effectNum = shopDao.updateShop(shop);
                    if (effectNum <= 0) {
                        throw new ShopOperationException("更新图片失败");
                    }
                }
            }
        }
        catch (Exception e) {
            throw new ShopOperationException("addShop Error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }


    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        String dest = PathUtil.getShopImagPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }
}
