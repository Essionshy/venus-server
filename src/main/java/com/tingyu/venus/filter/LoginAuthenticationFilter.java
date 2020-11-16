package com.tingyu.venus.filter;

import com.tingyu.venus.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 登录认证过滤器
 * @Author essionshy
 * @Create 2020/11/10 21:44
 * @Version venus-server
 */
@Slf4j
public class LoginAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        if(servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse){
            HttpServletRequest request= (HttpServletRequest) servletRequest;
            HttpServletResponse  response= (HttpServletResponse) servletResponse;
            //判断用户是否登录

            String token = request.getHeader("token");
            //验证token是否合法有效
            JwtUtils.validate(token);
            //如果没有异常发生，则放行


            chain.doFilter(request,response);
        }








    }

    @Override
    public void destroy() {

    }
}
