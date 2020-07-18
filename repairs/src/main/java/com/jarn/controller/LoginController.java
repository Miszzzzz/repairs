package com.jarn.controller;

import com.jarn.common.constant.Constant;
import com.jarn.common.cache.SessionCache;
import com.jarn.entity.User;
import com.jarn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author WZY
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    /**
     * 进入首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }


    /**
     * 进入登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(String userName, String password, HttpServletRequest request){
        User user = null;
        try {
            user = userService.login(userName, password);
            //信息存入session
            SessionCache.setUser(request, user);
            request.getSession().setAttribute(Constant.SESSION_USER, user);
        }catch (Exception e){
            //
        }
        return user;
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut", method = {RequestMethod.GET})
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        SessionCache.removeUser(request);
        request.getSession().removeAttribute(Constant.SESSION_USER);
        return "redirect:login";
    }

    @RequestMapping(value = "/loginMsg")
    public Object loginMsg(HttpServletRequest request){
        return null;
    }
}
