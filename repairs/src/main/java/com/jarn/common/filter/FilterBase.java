package com.jarn.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/user/*","/admin/*"})
public class FilterBase implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterBase执行");
        HttpServletResponse response = (HttpServletResponse)resp;
        response.setHeader("Cache-Control","no-cache,no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", -1);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
