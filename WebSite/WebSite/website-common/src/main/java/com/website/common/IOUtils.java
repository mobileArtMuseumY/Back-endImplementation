package com.website.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: WebSite
 * @description: 文件流转换工具类
 * @author: smallsoup
 * @create: 2018-07-08 14:55
 **/

public class IOUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);

    public static void inputStreamToFile(InputStream is, File file) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            int read;

            byte[] buffer = new byte[1024];

            while ((read = is.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }

            fos.flush();
        } catch (IOException e) {
            LOGGER.error("inputStreamToFile error！{}", e);
            throw new RuntimeException("inputStreamToFile error " + e.getMessage());
        } finally {

            try {
                if (null != fos) {
                    fos.close();
                }

                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                LOGGER.error("inputStreamToFile close error！{}", e);
                throw new RuntimeException("inputStreamToFile close error " + e.getMessage());
            }
        }

    }
}
