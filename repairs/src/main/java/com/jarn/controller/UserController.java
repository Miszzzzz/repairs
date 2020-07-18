package com.jarn.controller;

import com.jarn.common.constant.Constant;
import com.jarn.common.util.CommonUtils;
import com.jarn.common.util.PaginationUtils;
import com.jarn.entity.Maintenance;
import com.jarn.entity.PageBean;
import com.jarn.entity.ResultInfo;
import com.jarn.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author WZY
 **/
@Controller
@RequestMapping(path = "/user")
public class UserController extends BaseController{

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping(value = "/home")
    public String home(){
        return "user/user";
    }

    @RequestMapping(value = "/home_user")
    public String home_user(){
        return "user/home_user";
    }

    @RequestMapping(value = "/home_main")
    public String home_main(){
        return "user/home_main";
    }

    @RequestMapping(value = "/info")
    public String home_main_info(){
        return "user/info";
    }

    @RequestMapping(value = "/saveRepairs", method = RequestMethod.POST)
    @ResponseBody
    public Object saveRepairs(HttpServletRequest request){
        return userService.saveMaintenaceAdnImage(request);
    }

    @RequestMapping(value = "/pagination")
    @ResponseBody
    public Object PaginationUser(int pageNum, HttpServletRequest request){
        PaginationUtils<Maintenance> paginationUtils = new PaginationUtils<>();
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER);

        PageBean<Maintenance> pageBean = paginationUtils.UserPaginationData(
                userService, pageNum, Constant.PAGE_SIZE, user.getUid());
        if (CommonUtils.isEmpty(pageBean)){
            return new ResultInfo(false);
        }
        return pageBean;
    }

    @RequestMapping(value = "/PaginationAdmin")
    @ResponseBody
    public Object PaginationAdmin(int pageNum, String keyWords, HttpServletRequest request){
        PaginationUtils<Maintenance> paginationUtils = new PaginationUtils<>();
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER);

        PageBean<Maintenance> pageBean = paginationUtils.basePaginationData(
                userService, pageNum, Constant.PAGE_SIZE, keyWords, null, user.getUid());
        if (CommonUtils.isEmpty(pageBean)){
            return new ResultInfo(false);
        }
        return pageBean;
    }

    @RequestMapping(value = "/findMainInfo")
    @ResponseBody
    public Object findMaintenanceByMid(int mId){
        Maintenance maintenance = userService.findMaintenanceByMid(mId);
        return maintenance;
    }

    @RequestMapping(value = "/evaluation")
    @ResponseBody
    public Object evaluation(String evaluation, int mId){
        userService.evaluation(evaluation, mId);
        return new ResultInfo();
    }
}
