package com.artmall.shiro.filter;


import com.artmall.exception.ArtmallException;
import com.artmall.exception.TokenException;
import com.artmall.response.ResponseCode;
import com.artmall.shiro.JWT.JWTToken;
import com.artmall.utils.FilterUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @create 2018-08-09 14:32
 **/

public class JWTFilter extends BasicHttpAuthenticationFilter {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    int flag = 0;
    /**
     * 判断用户是否想要登入。
     * 拦截ajax请求，判断shi
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        if (authorization==null){
            exception(response,"请输入token");
        }
        return authorization != null;
    }

    /**
     * 执行登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取token
        String authorization = httpServletRequest.getHeader("Authorization");
        String url = httpServletRequest.getRequestURI();

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        LOGGER.debug("拦截开始");


        if (isLoginAttempt(request, response)) {


            try {
                executeLogin(request, response);
                flag = 1;
            } catch (Exception e) {
                return false;
            }
            //获取当前subject
            Subject subject = this.getSubject(request, response);

            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String perm = httpServletRequest.getRequestURI();

            if (perm != null && perm.length()>0){
                if (subject.isPermitted(perm)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 对返回的false进行处理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        if (flag == 0){
            exception(response,"token无效");
        }else {
            exception(response,"没有此权限");
        }
        return false;
    }

    private void exception(ServletResponse response,String meg) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status:",ResponseCode.TOKEN_OUT_OF_DATE.getCode());
        resultMap.put("mes:",meg);
        FilterUtil.out(response,resultMap);
    }

//    /**
//     * 对跨域提供支持
//     */
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
//        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpServletResponse.setStatus(HttpStatus.OK.value());
//            return false;
//        }
//        return super.preHandle(request, response);
//    }
//
    /**
     * 将非法请求跳转到 /401
     */
//    private ServerResponse response401(ServletRequest req, ServletResponse resp) {
//        try {
//            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
//            httpServletResponse.sendRedirect("/401");
//        } catch (Exception e) {
//            return ServerResponse.NoPermitted("没有此权限");
//        }
//    }
}
