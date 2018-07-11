package com.website.common;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: WebSite
 * @description: 发送http请求工具类
 * @author: smallsoup
 * @create: 2018-07-01 11:57
 **/
public class HttpRequestUtils {

    private static HttpRequestUtils instance = null;

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(5000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .setExpectContinueEnabled(true)
            .build();

    private HttpRequestUtils() {
    }

    public static HttpRequestUtils getInstance() {
        if (instance == null) {
            instance = new HttpRequestUtils();
        }
        return instance;
    }

    /**
     * HttpClient 模拟POST请求
     *
     * @param url
     * @param params
     * @return
     * @Discription:发送POST请求
     */
    public static String postRequest(String url, Map<String, String> params) throws IOException {
        //构造HttpClient的实例
        CloseableHttpClient client = HttpClients.createDefault();


        //创建POST方法的实例
        HttpPost httpPost = new HttpPost(url);


        //设置请求头信息
        httpPost.setHeader("Connection", "close");

        List<NameValuePair> nvps = new ArrayList<>();
        //添加参数
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

        //使用系统提供的默认的恢复策略,设置请求重试处理，用的是默认的重试处理：请求三次
//        client.getParams().setBooleanParameter("http.protocol.expect-continue", false);

        return executeRequest(client, httpPost);
    }


    /**
     * HttpClient 模拟GET请求
     * 方法说明
     *
     * @param url
     * @param params
     * @return String
     * @Discription:发送GET请求
     */
    public static String getRequest(String url, Map<String, String> params) throws IOException {
        //构造HttpClient实例
        CloseableHttpClient client = HttpClients.createDefault();


        //拼接参数
        StringBuilder paramStr = new StringBuilder();
        for (String key : params.keySet()) {
            paramStr.append("&").append(key).append("=").append(params.get(key));
        }

        //创建GET方法的实例
        HttpGet method = new HttpGet(url + "?" + paramStr.toString().substring(1));

        return executeRequest(client, method);
    }

    private static String executeRequest(CloseableHttpClient client, HttpRequestBase method) throws IOException {

        method.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
        method.setConfig(requestConfig);

        //接收返回结果
        String result = null;

        try {
            //执行HTTP GET方法请求
            final HttpResponse response = client.execute(method);

            //返回处理结果
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity, Consts.UTF_8);
        } catch (IOException e) {
            // 发生网络异常
            System.out.println("发生网络异常!");
            e.printStackTrace();
        } finally {
            //释放链接
            method.releaseConnection();

            //关闭HttpClient实例
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

}


