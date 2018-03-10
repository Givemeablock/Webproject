package com.yyc.webpro.util;

public class PathUtil {

    private static String separator = System.getProperty("file.separator");


    /**
     * 根据系统来区分图片存储的绝对路径
     * @return
     */
    public static String getImgBasePath() {

        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "G:/Documents/Personal/Pic/MavenProPic/";
        }
        else {
            basePath = "/home/mavenpro/pic/";
        }

        basePath.replace("/", separator);
        return basePath;
    }

    /**
     * 根据shopId来获取shop的相对路径
     * @param ShopId
     * @return
     */
    public static String getShopImagPath(long ShopId) {

        String imagePath = "upload/item/shop/" + ShopId + "/";

        return imagePath.replace("/", separator);
    }
}
