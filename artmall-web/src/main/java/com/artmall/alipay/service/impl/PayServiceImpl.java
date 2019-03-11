package com.artmall.alipay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.artmall.DTO.AlipayBean;
import com.artmall.alipay.config.AlipayConfig;
import com.artmall.alipay.config.AlipayUtil;
import com.artmall.alipay.service.PayService;

import org.springframework.stereotype.Service;

/* 支付服务 */
@Service
public class PayServiceImpl implements PayService {

    /**
     * 统一收单下单并支付页面接口，生成前台页面请求需要的完整的form表单的html
     * 支付支付请求
     * @param
     * @return
     * @throws AlipayApiException
     */
    public String pay(AlipayBean alipayBean){
//        if(alipayBean==null)return "error";
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.getKey("return_url")); //页面跳转同步通知页面路径
        System.out.println("return_url is :"+alipayRequest.getReturnUrl());
        alipayRequest.setNotifyUrl(AlipayConfig.getKey("notify_url"));// 服务器异步通知页面路径
        System.out.println("notify_url is:"+alipayRequest.getNotifyUrl());
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

        System.out.println(alipayRequest.getBizContent());
        //请求支付宝进行付款
        String result = null;
        try {
            result = AlipayUtil.getAlipayClient().pageExecute(alipayRequest).getBody();//调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //返回付款信息
        return  result;//返回的是支付宝的页面

    }

    /**
     * 查询订单
     * @param out_trade_no  商户网站唯一订单号
     * @param trade_no 这笔交易在支付宝系统中的交易流水号，最长64位
     * @return
     */
    public String query(String out_trade_no,String trade_no){
        if(out_trade_no==null||trade_no==null)return "error";
        AlipayTradeQueryRequest alipayTradeQueryRequest=new AlipayTradeQueryRequest();
        AlipayTradeQueryModel alipayTradeQueryModel=new AlipayTradeQueryModel();
        //封装参数
        alipayTradeQueryModel.setOutTradeNo(out_trade_no);
        alipayTradeQueryModel.setTradeNo(trade_no);
        alipayTradeQueryRequest.setBizModel(alipayTradeQueryModel);
        AlipayTradeQueryResponse alipay_response = null;
        try {
            alipay_response = AlipayUtil.getAlipayClient().execute(alipayTradeQueryRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        String result =alipay_response.getBody();
        return result;//返回的是支付宝的页面
    }
    //

    /**
     * 交易关闭
     * 商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
     * @param out_trade_no  商户网站唯一订单号
     * @param trade_no 这笔交易在支付宝系统中的交易流水号，最长64位
     * @return
     */
    public String close(String out_trade_no,String trade_no){
        if(out_trade_no==null&&trade_no==null)return "error";
        AlipayTradeCloseRequest alipay_request=new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model =new AlipayTradeCloseModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        alipay_request.setBizModel(model);
        AlipayTradeCloseResponse alipay_response= null;
        try {
            alipay_response = AlipayUtil.getAlipayClient().execute(alipay_request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        String result=alipay_response.getBody();
        return result;
    }
    /**
     * 账单下载
     * @param bill_type // "trade"指商户基于支付宝交易收单的业务账单,"signcustomer"是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
     * @param bill_date // 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM
     * @return
     */
    public String downBill(String bill_type,String bill_date){
        AlipayDataDataserviceBillDownloadurlQueryRequest alipay_request = new AlipayDataDataserviceBillDownloadurlQueryRequest();

        AlipayDataDataserviceBillDownloadurlQueryModel model =new AlipayDataDataserviceBillDownloadurlQueryModel();
        model.setBillType(bill_type);
        model.setBillDate(bill_date);
        alipay_request.setBizModel(model);

        AlipayDataDataserviceBillDownloadurlQueryResponse alipay_response = null;
        try {
            alipay_response = AlipayUtil.getAlipayClient().execute(alipay_request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        String result = alipay_response.getBillDownloadUrl();
        return result;
    }
}
