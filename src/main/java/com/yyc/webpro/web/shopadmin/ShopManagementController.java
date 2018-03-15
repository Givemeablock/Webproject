package com.yyc.webpro.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyc.webpro.dto.ShopExecution;
import com.yyc.webpro.entity.Area;
import com.yyc.webpro.entity.PersonInfo;
import com.yyc.webpro.entity.Shop;
import com.yyc.webpro.entity.ShopCategory;
import com.yyc.webpro.enums.ShopStateEnum;
import com.yyc.webpro.exception.ShopOperationException;
import com.yyc.webpro.service.AreaService;
import com.yyc.webpro.service.ShopCategoryService;
import com.yyc.webpro.service.ShopService;
import com.yyc.webpro.util.CodeUtil;
import com.yyc.webpro.util.HttpRequestUtil;
import com.yyc.webpro.util.ImageUtil;
import com.yyc.webpro.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo")
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();

        try {
            //返回有父类的分类，因为所有都存在二级目录下
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        }
        catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if(!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "错误的验证码");
            return modelMap;
        }
        //1.接受并转化相应的参数，包括店铺和图片
        String shopStr = HttpRequestUtil.getString(request, "shopStr");
        System.out.println(shopStr);
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;

        //接收文件流
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().
                getServletContext());

        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest msquest = (MultipartHttpServletRequest)request;
            shopImg = (CommonsMultipartFile)msquest.getFile("shopImg");
        }
        else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传不能为空");
            return modelMap;
        }

        try {
            shop = mapper.readValue(shopStr, Shop.class);
        }
        catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            System.out.println(e.getMessage());
            return modelMap;
        }
        //System.out.println(shop);
        //2.注册店铺
        if (shop != null && !shopImg.isEmpty()) {
            //To-do  改为session
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
//            File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
//            try {
//                shopImgFile.createNewFile();
//            } catch (IOException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", e.getMessage());
//                return modelMap;
//            }
//            try {
//                inputStreamToFile(shopImg.getInputStream(), shopImgFile);
//            } catch (IOException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", e.getMessage());
//                return modelMap;
//            }

            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateinfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            }
             return modelMap;
        }
        else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }

        //3.返回结果
    }

//    private static void inputStreamToFile(InputStream ins, File file) {
//        FileOutputStream os = null;
//        try{
//            os = new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = ins.read(buffer)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//        }
//        catch (Exception e) {
//            throw new RuntimeException("调用InputStreamToFile异常" + e.getMessage());
//        }
//        finally {
//            try {
//                if (os != null) {
//                    os.close();
//                }
//                if (ins != null) {
//                    ins.close();
//                }
//            }
//            catch (IOException e){
//                throw new RuntimeException("调用InputStreamToFile异常" + e.getMessage());
//            }
//
//
//        }
//    }


}
