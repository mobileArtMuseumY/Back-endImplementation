package com.website.common.constant;

import java.nio.charset.Charset;

/**
 * @program: WebSite
 * @description: 全局常量类
 * @author: smallsoup
 * @create: 2018-06-30 10:48
 **/

public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset GBK = Charset.forName("GBK");
    public static final Charset ASCII = Charset.forName("US-ASCII");
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    private Const() {}

}
