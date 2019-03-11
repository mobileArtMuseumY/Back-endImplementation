package com.artmall.alipay.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 阿里端口调用配置
 *
 * @author mllove
 * @create 2018-11-04 17:54
 **/
@Component
public class AlipayConfig implements ApplicationListener {
    //保存加载配置参数
    private static Map<String, String> aliPropertiesMap = new HashMap<String, String>();

    /*获取配置参数值*/
    public static String getKey(String key) {
        return aliPropertiesMap.get(key);
    }

    /*监听启动完成，执行配置加载到aliPropertiesMap*/
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            this.init(aliPropertiesMap);//应用启动加载
        }
    }

    /*初始化加载aliPropertiesMap*/
    public void init(Map<String, String> map) {
        // 获得PathMatchingResourcePatternResolver对象
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //加载支付配置resource文件(也可以加载resources)
            Resource resources = resolver.getResource("classpath:alipay.properties");
            PropertiesFactoryBean config = new PropertiesFactoryBean();
            config.setLocation(resources);
            config.afterPropertiesSet();
            Properties prop = config.getObject();
            //循环遍历所有得键值对并且存入集合
            for (String key : prop.stringPropertyNames()) {
                map.put(key, (String) prop.get(key));
            }
        } catch (Exception e) {
            new Exception("配置文件加载失败");
        }
    }


}
