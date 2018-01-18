package com.sara.examenopi.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        res.setHeader("Access-Control-Allow-Origin",  req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-requested-with, Authorization, X-Auth-Token, Content-Type,Accept, Cache-Control");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        
        if(req.getMethod().equalsIgnoreCase("OPTIONS")){
            res.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
    
}
