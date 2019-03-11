package com.artmall.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.artmall.DO.OrderForm;
import com.artmall.DTO.AlipayBean;

import com.artmall.alipay.config.AlipayConfig;
import com.artmall.alipay.service.PayService;


import com.artmall.email.EmailService;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.OrderService;
import com.artmall.service.StudentService;
import com.artmall.utils.RequestUtils;
import org.apache.catalina.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.security.rsa.RSASignature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




/* 订单接口 */
@Controller
@RequestMapping("/alipay")
public class PayController {
    private final static Logger log = LoggerFactory.getLogger(PayController.class);
    @Autowired
    private PayService payService;//调用支付服务

    /**
     *  支付宝支付请求页面
     * @return
     * @throws AlipayApiException
     */
    /*阿里支付*/
    @RequestMapping("/pay")
    @ResponseBody
    public String alipayPay(@RequestBody AlipayBean bean) throws AlipayApiException {
        log.debug("pay");
        return payService.pay(bean);
    }
//    public String alipayPay()throws AlipayApiException{
//        return payService.pay();
//    }

    /**
     * 同步商户验签，返回验签的状态
     * 返回同步返回参数
     *页面回跳参数
     * @return
     */
    @RequestMapping(value = "/synchronized/notic",method = RequestMethod.GET)

    public String alipaySynchronizedResponse(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
            log.debug("同步返回");
            System.out.println("同步返回");
            //把request的数据放入map里面
            Map<String,String> params = RequestUtils.requestToMap(request);

            for (Map.Entry<String,String> newentry :params.entrySet()){
                log.debug(newentry.getKey()+"="+newentry.getValue().toString());
            }

        //验签成功
        //调用sdk，验签
        Boolean b = AlipaySignature.rsaCheckV1(params, AlipayConfig.getKey("alipay_public_key"),AlipayConfig.getKey("charset"),AlipayConfig.getKey("sign_type"));



        if(b){//验证成功
            response.sendRedirect("localhost:8081/#/signin");
            return "success";
//                    Const.IP_ADDRESS+ "/#/user/enterpriseHomepage";
        }else{
            response.sendRedirect("localhost:8081/#/pay/response/failure");
            return "error";
       }

    }
    /**
     *异步调用验签，最终的以异步调用的为主
     * @return
     */
    @Autowired
    OrderService orderService;
    @Autowired
    EmailService emailService;
    @Autowired
    StudentService studentService;
    @RequestMapping(value = "/notify/notic",method = RequestMethod.POST)
    @ResponseBody
    public String alipayNotifylResponse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("异步调用签验");
        Map<String,String> params = RequestUtils.requestToMap(request);
        for (Map.Entry<String,String> entry1 :params.entrySet()){
            System.out.println(entry1.getKey()+"="+entry1.getValue());
        }

        String trade_status = params.get("trade_status");

        Boolean b = AlipaySignature.rsaCheckV1(params, AlipayConfig.getKey("alipay_public_key"),AlipayConfig.getKey("charset"),AlipayConfig.getKey("sign_type"));
        //验签成功
        if(b){
            log.debug("验签成功");

            //订单完成
            if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){

                log.debug("trade_status is finished");
                /**
                 * 交易结束，不可退款
                 * 修改订单状态
                 * 给双方发送邮件
                 * 学生交易次数加一
                 */
                OrderForm order = orderService.pay(params.get("out_trade_no"));
                emailService.payFinish(order);
                studentService.addTransTime(order.getSellerId());

            } else if(trade_status.equals("TRADE_CLOSED"));

        }
        return "success";//一定要传回success,如果商户反馈给支付宝的字符不是success这7个字符，支付宝服务器会不断重发通知
    }

    /**
     * 交易查询
     * @param out_trade_no  商户网站唯一订单号
     * @param trade_no 这笔交易在支付宝系统中的交易流水号，最长64位
     * @return
     */
    @RequestMapping("/qurey")
    @ResponseBody
    public String alipayQurey(@RequestParam("out_trade_no") String out_trade_no, @RequestParam("trade_no")String trade_no){
        return payService.query(out_trade_no,trade_no);
    }
    /**
     * 交易关闭查询
     * @param out_trade_no  商户网站唯一订单号
     * @param trade_no 这笔交易在支付宝系统中的交易流水号，最长64位
     * @return
     */
    @RequestMapping("/close")
    @ResponseBody
    public String alipayClose(@RequestParam("out_trade_no") String out_trade_no, @RequestParam("trade_no")String trade_no){
        return payService.close(out_trade_no,trade_no);
    }

    /**
     * 账单下载，默认trade,月账单格式，当bill_type设置为1时，为signcustomer，bill_date设置为1时，为日账单格式
     * @param bill_type // "trade"指商户基于支付宝交易收单的业务账单,"signcustomer"是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
     * @param bill_date // 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM
     * @return
     */
    @RequestMapping("/downbill")
    @ResponseBody
    public String alipayDownBill(@RequestParam("bill_type")@Nullable String bill_type, @RequestParam("bill_date")@Nullable String bill_date){
        String bill_typeS="trade",bill_dateS="yyyy-MM";
        if(bill_type.equals("1")) bill_typeS="signcustomer";
        if(bill_date.equals("1")) bill_dateS="yyyy-MM-dd";
        return payService.downBill(bill_typeS,bill_dateS);
    }
}
