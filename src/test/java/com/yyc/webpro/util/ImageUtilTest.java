package com.yyc.webpro.util;

import com.yyc.webpro.BasicTest;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageUtilTest extends BasicTest {


    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    @Test
    public void testImage(){

        try {
            Thumbnails.of(new File("G:\\Documents\\Personal\\Pic\\MavenProPic\\test1.jpg")).size(400,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
                    .outputQuality(0.8f).toFile("G:\\Documents\\Personal\\Pic\\MavenProPic\\test1-copy.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
