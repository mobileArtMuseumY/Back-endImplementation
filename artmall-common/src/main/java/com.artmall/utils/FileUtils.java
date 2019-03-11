package com.artmall.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Random;

/**
 * 随机生成文件名
 *
 * @author mllove
 * @create 2018-09-30 18:41
 **/

public class FileUtils {

    public static String creatFileName(MultipartFile file) {
        Random random = new Random();
        int length = 15;
        String numstr = "123456789";
        String chastr_b = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String chastr_s = "abcdefghijklmnopqrstuvwxyz";
        String specil = "_";
        String base = numstr + chastr_b + chastr_s + specil;
        //文件夹名的规范文件夹不能包含以下字符：
        //井号 (#)；百分号 (%)；“&”；星号 (*)；竖线 (|)；反斜杠 (\)；冒号(:)；
        //双引号 (")；小于号 (<)；大于号 (>)；问号 (?)；斜杠 (/)；前导或尾随空格 (' ')；这样的空格将被去除；
        //需求是将文件名的大写开头，数字结尾
        StringBuffer sb = new StringBuffer();

        sb.append(chastr_b.charAt(random.nextInt(chastr_b.length())));
        for (int i = 0; i < length - 2; i++) {
            int num = random.nextInt(base.length());
            sb.append(base.charAt(num));
        }
        //追加最后一个数字
        sb.append(numstr.charAt(random.nextInt(numstr.length())));
        //System.out.println(sb.toString());

        String oldFileName = file.getOriginalFilename();
        //获取后缀名
        String res = oldFileName.substring(oldFileName.lastIndexOf(".")+1).toLowerCase();

        String fileName = sb.toString()+"."+res;
        return fileName;

    }
}
