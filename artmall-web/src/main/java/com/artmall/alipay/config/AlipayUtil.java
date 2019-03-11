package com.artmall.alipay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;

/* 支付宝常用类 */
public class AlipayUtil {
    //1、获得初始化的AlipayClient,只需要初始化一次，后面都可以直接用
    private static AlipayClient alipayClient = new DefaultAlipayClient(
            AlipayConfig.getKey("gatewayUrl"),//支付宝网关
            AlipayConfig.getKey("app_id"),//appid
            AlipayConfig.getKey("merchant_private_key"),//商户私钥
            AlipayConfig.getKey("format"),//json
            AlipayConfig.getKey("charset"),//字符编码格式
            AlipayConfig.getKey("alipay_public_key"),//支付宝公钥
            AlipayConfig.getKey("sign_type")//签名方式
    );
    public static AlipayClient getAlipayClient(){
        return alipayClient;
    }

    /**
     * 验签
     * @param c
     * @return
     */
    public static Boolean cheackSign(Object c){
        //格式化字符串
        ArrayList<String> list= (ArrayList<String>) AlipayUtil.getNameParams(c);
        String sign="";
        String context="";
        for (String str:list) {
            //取出sign
            if(str.startsWith("sign=")){
                sign=str;
                continue;
            }
            context=str+"&";
        }
        if(context.endsWith("&"))context.substring(0,context.length()-1);
        Boolean b=false;
        try {
            b=AlipaySignature.rsaCheckContent(context,sign,AlipayConfig.getKey("alipay_public_key"),"utf-8");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return b;
    }
    /**
     * 将一个javabean的非空数据按键值对name=value的形式装进List<String>
     * 并且是对字符串进行排序
     * @param
     * @return
     */
    private static ArrayList<String> getNameParams(Object obj){
        Class c=obj.getClass();
        ArrayList<String> list=new ArrayList<String>();
        Field []fields=c.getDeclaredFields();
        for (Field field:fields) {
            String name=field.getName();
            field.setAccessible(true);
            try {
                String value=(String)field.get(obj);
                if(value==""||value==null)continue;
                if(name.contains(" "))name.replace(" ","");
                if(value.contains(" "))value.replace(" ","");
                list.add(name+"="+value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return ((String)o1).compareTo((String)o2);
            }
        });
        return list;
    }
}
