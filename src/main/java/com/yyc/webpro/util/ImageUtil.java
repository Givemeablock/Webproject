package com.yyc.webpro.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random r = new Random();

    /**
     *
     * @param thumbnailInputStream
     * @param fileName
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName,  String targetAddr) {

        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnailInputStream).size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(
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
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
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

    /**
     * 若是文件路径，删除文件，若是目录路径，删除所有文件
     */

    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i ++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

//    public static void main(String[] args) {
//        try {
//            Thumbnails.of(new File("G:\\Documents\\Personal\\Pic\\MavenProPic\\test1.jpg")).size(400,200)
//                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
//                    .outputQuality(0.8f).toFile("G:\\Documents\\Personal\\Pic\\MavenProPic\\test1-copy.jpg");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
