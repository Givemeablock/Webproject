package com.yyc.webpro.util;

public class PathUtil {

    private static String separator = System.getProperty("file.separator");

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

    public static String getShopImagPath(long ShopId) {

        String imagePath = "/upload/item/shop/" + ShopId + "/";

        return imagePath.replace("/", separator);
    }
}
