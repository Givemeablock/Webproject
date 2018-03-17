package com.yyc.webpro.service.impl;

import com.yyc.webpro.dao.ShopDao;
import com.yyc.webpro.dto.ShopExecution;
import com.yyc.webpro.entity.Shop;
import com.yyc.webpro.enums.ShopStateEnum;
import com.yyc.webpro.exception.ShopOperationException;
import com.yyc.webpro.service.ShopService;
import com.yyc.webpro.util.ImageUtil;
import com.yyc.webpro.util.PageCalculator;
import com.yyc.webpro.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
                        addShopImg(shop, shopImgInputStream, fileName);
                    }
                    catch (Exception e) {
                        throw new ShopOperationException("添加店铺图片失败" + e.getMessage());
                    }


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

    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
        //1.判断是否需要修改图片
        try {
            if ( shop == null || shop.getShopId() == null ) {
                return new ShopExecution(ShopStateEnum.NULL_SHOPINFO);
            }
            else {
                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);

                }
            }
            //2.更新店铺信息

            shop.setUpdateTime(new Date());
            int effectNum = shopDao.updateShop(shop);
            if (effectNum <= 0) {
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }
            else {
                shop = shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS, shop);
            }
        } catch (Exception e) {
            throw  new ShopOperationException("modify shop failed");
        }
    }

    /**
     * 根据condition返回数据
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if(shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        }
        else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }

        return se;
    }

    @Override
    public Shop getByShopId(long shopId) {
        Shop shop = shopDao.queryByShopId(shopId);
        return shop;
    }


    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        String dest = PathUtil.getShopImagPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }
}
