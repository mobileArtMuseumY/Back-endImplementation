package com.artmall.utils;

import org.springframework.http.MediaType;

import javax.servlet.ServletContext;

/**
 * 文件流格式
 *
 * @author mllove
 * @create 2018-11-21 15:19
 **/

public class MediaTypeUtils {
    // abc.zip
    // abc.pdf,..
    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // application/pdf
        // application/xml
        // image/gif, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
