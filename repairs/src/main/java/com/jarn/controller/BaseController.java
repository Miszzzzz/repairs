package com.jarn.controller;

import com.jarn.common.constant.Constant;
import com.jarn.entity.ResultInfo;
import com.jarn.entity.User;
import com.jarn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author WZY
 **/
@Controller
public class BaseController {

    @Autowired
    public UserService userService;

    /**
     * 发送session信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUser")
    @ResponseBody
    public Object getUser(HttpServletRequest request){
        return request.getSession().getAttribute(Constant.SESSION_USER);
    }

    /**
     * 查询数据库获取信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/findUser")
    @ResponseBody
    public Object findUser(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER);
        User u = userService.findUserByUid(user.getUid());
        return u;
    }

    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public Object updateUser(User user){
        System.out.println(user);
        ResultInfo info = new ResultInfo();
        try {
            userService.updateUser(user);
        }catch (Exception e){
            //
            info.setFlag(false);
        }
        return info;
    }

}
