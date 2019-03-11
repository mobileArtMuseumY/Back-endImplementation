package com.artmall.utils;

import java.io.*;

import com.artmall.exception.FileException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将文件夹打包压缩成zip格式
 *
 * @author mllove
 * @create 2018-11-22 9:42
 **/

public class ZipUtils {
    private final static Logger log = LoggerFactory.getLogger(ZipUtils.class);
   /* public static void createZip(String sourcePath,String zipPath){
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            zos.setEncoding("gbk");
            writeZip(new File(sourcePath),"",zos);
        }catch (FileNotFoundException e){
            log.error("创建zip文件失败",e);
        }finally {
            try {
                if(zos!=null){
                    zos.close();
                }
            }catch (IOException e){
                log.error("创建ZIP失败",e);
            }
        }
    }

    private static void writeZip(File file, String partentPath, ZipOutputStream zos) {
        if (file.exists()){
            if (file.isDirectory()){
                partentPath+=file.getName()+File.separator;
                File[] files = file.listFiles();
                if (files.length!=0){
                    for (File f:files){
                        writeZip(f,partentPath,zos);
                    }
                }else {
                    try {
                        zos.putNextEntry(new ZipEntry(partentPath));
                    }catch (IOException e){
                        throw new FileException("zip文件写入失败");
                    }
                }
            }else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(partentPath+file.getName());
                    zos.putNextEntry(ze);
                    byte [] content = new byte[1024];
                    int len;
                    while ((len = fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }
                }catch (FileNotFoundException e){
                    log.error("创建ZIP文件失败",e);
                }catch (IOException e){
                    log.error("创建ZIP文件失败",e);
                }finally {
                    try {
                        if(fis!=null){
                            fis.close();
                        }
                    }catch(IOException e){
                        log.error("创建ZIP文件失败",e);
                    }
                }
            }
        }
    }*/


    public static void zip(File sourceDir, File zipFile) {

        try {
            // create object of FileOutputStream
            FileOutputStream fout = new FileOutputStream(zipFile);

            // create object of ZipOutputStream from FileOutputStream
            ZipOutputStream zout = new ZipOutputStream(fout);
            addDirectory(zout, sourceDir, sourceDir);

            // close the ZipOutputStream
            zout.close();

            log.info("Zip file has been created!");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    /**
     * Add the directory recursively into a zip file
     * @param zout
     * @param fileSource
     * @param sourceDir
     */
    private static void addDirectory(ZipOutputStream zout, File fileSource, File sourceDir) {

        // get sub-folder/files list
        File[] files = fileSource.listFiles();

        log.debug("Adding directory " + fileSource.getName());

        for (int i = 0; i < files.length; i++) {
            try {
                String name = files[i].getAbsolutePath();
                name = name.substring((int) sourceDir.getAbsolutePath().length());
                // if the file is directory, call the function recursively
                if (files[i].isDirectory()) {
                    addDirectory(zout, files[i], sourceDir);
                    continue;
                }

                /*
                 * we are here means, its file and not directory, so
                 * add it to the zip file
                 */

                log.debug("Adding file " + files[i].getName());

                // create object of FileInputStream
                FileInputStream fin = new FileInputStream(files[i]);

                zout.putNextEntry(new ZipEntry(name));

                IOUtils.copy(fin, zout);

                zout.closeEntry();

                // close the InputStream
                fin.close();

            } catch (IOException ioe) {
                log.error(ioe.getMessage(), ioe);
            }
        }

    }
}
