package com.wbj.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.wbj.common.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 王兵杰
 * @date 2021/6/6
 */
@Slf4j
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        log.info(method);
        //浏览器第一次得测试请求
        final String getMethod = "OPTIONS";
        if (getMethod.equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("token");
        String password = request.getHeader("password");
        log.info("token:" + token);
        log.info("password:" + password);
        if (token == null) {
            doResponse(request, response, Result.error("请先登录"));
            return false;
        } else if (StringUtils.pathEquals("false", token)) {
            doResponse(request, response, Result.error("登录过期，请先登录"));
            return false;
        } else {
            JwtParser parser = Jwts.parser();
            //先解析设置的加密密码
            parser.setSigningKey(password);
            //解析token
            try {
                parser.parseClaimsJws(token);
                return true;
            } catch (ExpiredJwtException e) {
                doResponse(request, response, Result.error("登录过期，请先登录"));
                return false;
            } catch (UnsupportedJwtException e) {
                doResponse(request, response, Result.error("token不合法"));
                return false;
            } catch (Exception e) {
                doResponse(request, response, Result.error("token不合法"));
                return false;
            }
        }
    }

    /**
     *
     * @param response
     *
     * @throws IOException 方法封装
     */
    private void doResponse(HttpServletRequest request, HttpServletResponse response, Result result) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String s = new ObjectMapper().writeValueAsString(result);
        writer.print(s);
        writer.flush();
        writer.close();
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
