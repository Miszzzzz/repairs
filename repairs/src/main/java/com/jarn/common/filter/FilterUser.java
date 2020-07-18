package com.jarn.common.filter;

import com.jarn.common.constant.Constant;
import com.jarn.common.util.CommonUtils;
import com.jarn.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterUser implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterUser.........执行了");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER);
        if (CommonUtils.isEmpty(user)){
            response.sendRedirect(request.getContextPath()+Constant.LOGIN);
            return;
        }
        if (!CommonUtils.isEmpty(user) && user.getRid() == 2) {
            response.sendRedirect(request.getContextPath()+Constant.LOGIN_ADMIN);
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
