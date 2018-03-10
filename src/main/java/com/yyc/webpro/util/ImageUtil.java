package com.yyc.webpro.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random r = new Random();

    /**
     *
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(File thumbnail, String targetAddr) {

        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail).size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(
                    basePath + "/watermark.png")), 0.25f).outputQuality(0.8f).toFile(dest);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return dest.getPath();
    }


    /**
     * 生成随机文件名
     * @param
     */
    public static String getRandomFileName() {
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }


    /**
     * 获取文件扩展名
     * @param
     */
    private static String getFileExtension(File cFile) {
        String oriFileName = cFile.getName();
        return oriFileName.substring(oriFileName.lastIndexOf("."));
    }

    /**
     * 创建有效目录
     * @param
     *
     */
    private static void makeDirPath(String targetAddr) {
        String realFilePath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFilePath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }


    public static void main(String[] args) {
        try {
            Thumbnails.of(new File("G:\\Documents\\Personal\\Pic\\MavenProPic\\test1.jpg")).size(400,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
                    .outputQuality(0.8f).toFile("G:\\Documents\\Personal\\Pic\\MavenProPic\\test1-copy.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
