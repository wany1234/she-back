package com.she.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getMethod().toUpperCase().equals("GETxxxxx")) {
            // 필터적용
            ((HttpServletResponse) res).setHeader("Pragma", "no-cache");
            ((HttpServletResponse) res).setHeader("Expires", "-1");
            ((HttpServletResponse) res).setCharacterEncoding("UTF-8");
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(javax.servlet.FilterConfig fc) throws ServletException {
    }
}
