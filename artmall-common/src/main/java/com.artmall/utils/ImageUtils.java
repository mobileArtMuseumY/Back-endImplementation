package com.artmall.utils;

/**
 * 图片处理
 *
 * @author mllove
 * @create 2018-09-11 10:44
 **/

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * 生成缩略图
 * 添加水印
 */
public class ImageUtils {
    private final static Logger log = LoggerFactory.getLogger(ImageUtils.class);
//    private static String IMAGE = "D:\\java\\project\\artmall\\image\\";
    private static String IMAGE = "//root//artmall//image//";

//    //存放从前端获取的原图
//    private static String IMAGE_PATH = IMAGE+"upload-dir\\";
    //存放水印
    private static String IMAGE_WARTER_MARK = IMAGE+"water-mark//wartmarkTest_0001_hello-world.png";
//    //存放加了水印的图片
//    private static String IMAGE_WARTER_MART_SAVA_PATH= IMAGE+"save-water-mark\\";
//    //存放works的封面图片(加了水印后的)
//    private static String IMAGE_DIRECTORY=IMAGE+"image-directory\\";
//    //存放works封面图片的缩略图
//    private static String IMAGE_DIRECTORY_THUMBNAIL=IMAGE+"image-directory-thumbnail\\";

    public static String getIMAGE() {
        return IMAGE;
    }

//    public static String getImagePath() {
//        return IMAGE_PATH;
//    }

    public static String getImageWarterMark() {
        return IMAGE_WARTER_MARK;
    }

//    public static String getImageWarterMartSavaPath() {
//        return IMAGE_WARTER_MART_SAVA_PATH;
//    }
//
//    public static String getImageDirectory() {
//        return IMAGE_DIRECTORY;
//    }
//
//    public static String getImageDirectoryThumbnail() {
//        return IMAGE_DIRECTORY_THUMBNAIL;
//    }

    /**
     * 对原图加水印
     * @param path     原图
     * @param watermarkPath  加水印后的图片
     */
    public static void  generateWatermark(String path,String watermarkPath){
        try{
            System.out.println("generateWatermark");
            Thumbnails.of(path)
                    .size(600,600)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(IMAGE_WARTER_MARK)),1f)
                    .outputQuality(0.7)
                    .toFile(watermarkPath);

        }catch (IOException E){
            log.error(E.getMessage());
        }

    }

    /**
     * 将指定目录下的图片全部生成缩略图
     * 固定宽度，按比例缩放
     */
    public static void generateDirectoryThumbnail(String oldPicture,String newPicture){
        try{
            Thumbnails.of(
                    new File(oldPicture))
                    .size(320,320)
                    .outputQuality(0.9)
                    .toFile(newPicture);
        }catch (IOException e) {
            log.error(e.getMessage());
        }
    }



}
