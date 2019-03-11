package com.artmall.alipay.service;

import com.alipay.api.AlipayApiException;
import com.artmall.DTO.AlipayBean;

/*支付服务*/
public interface PayService {

    /**
     * 支付支付请求
     * @param
     * @return
     * @throws AlipayApiException
     */
    public String pay(AlipayBean alipayBean);

    /**
     * 查询订单
     * @param out_trade_no  商户网站唯一订单号
     * @param trade_no 这笔交易在支付宝系统中的交易流水号，最长64位
     * @return
     */
    public String query(String out_trade_no, String trade_no);

    /**
     * 交易关闭
     * 商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
     * @param out_trade_no  商户网站唯一订单号
     * @param trade_no 这笔交易在支付宝系统中的交易流水号，最长64位
     * @return
     */
    public String close(String out_trade_no, String trade_no);
    /**
     * 账单下载
     * @param bill_type // "trade"指商户基于支付宝交易收单的业务账单,"signcustomer"是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
     * @param bill_date // 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM
     * @return
     */
    public String downBill(String bill_type, String bill_date);
}
