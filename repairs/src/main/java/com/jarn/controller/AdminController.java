package com.jarn.controller;

import com.jarn.common.constant.Constant;
import com.jarn.common.enums.RoleEnum;
import com.jarn.common.enums.StatusEnum;
import com.jarn.common.util.CommonUtils;
import com.jarn.common.util.PaginationUtils;
import com.jarn.entity.*;
import com.jarn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author WZY
 **/
@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController{

    @Autowired
    private AdminService adminService;

    //页面跳转
    @RequestMapping(value = "/home")
    public String home(){
        return "admin/admin";
    }

    @RequestMapping(value = "/user")
    public String user(){
        return "admin/admin_user";
    }

    @RequestMapping(value = "/mainCe")
    public String mainCe(){
        return "admin/admin_mainCe";
    }

    @RequestMapping(value = "/mainEr")
    public String mainEr(){
        return "admin/admin_mainEr";
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping(value = "/findAllUser")
    @ResponseBody
    public Object findAllUser(int pageNum){
        PaginationUtils<User> pagination = new PaginationUtils<>();
        PageBean<User> pageBean = pagination.UserPaginationData(adminService, pageNum, Constant.PAGE_SIZE_USER);
        if (CommonUtils.isEmpty(pageBean)){
            return new ResultInfo(false);
        }
        return pageBean;
    }

    @RequestMapping(value = "/findUsername")
    @ResponseBody
    public Object findUsername(String username){
        ResultInfo info = new ResultInfo();
        info.setErrorMsg("用户名可以");
        User user = adminService.findUsername(username);
        if (!CommonUtils.isEmpty(user)) {
            info.setFlag(false);
            info.setErrorMsg("用户名已经存在");
        }
        return info;
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveUser")
    @ResponseBody
    public Object saveUser(User user){
        user.setRid(RoleEnum.USER.getCode());
        ResultInfo info = new ResultInfo();
        int i = adminService.saveUser(user);
        if (i == 0) info.setFlag(false);
        return info;
    }

    /**
     * 根据uid更新用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public Object updateUser(User user){
        ResultInfo info = new ResultInfo();
        int i = adminService.updateUser(user);
        if (i == 0) info.setFlag(false);
        return info;
    }

    /**
     * 根据uid删除用户
     * @param uid
     * @return
     */
    @RequestMapping(value = "/delUser")
    @ResponseBody
    public Object delUser(int uid){
        ResultInfo info = new ResultInfo();
        int i = adminService.delUser(uid);
        if (i == 0) info.setFlag(false);
        return info;
    }

    /**
     * 分页保修单数据
     * @param pageNum
     * @param keyWords
     * @param status
     * @return
     */
    @RequestMapping(value = "/adminMain")
    @ResponseBody
    public Object adminMain(int pageNum, String keyWords, String status){
        PaginationUtils<Maintenance> pagination = new PaginationUtils<>();
        PageBean<Maintenance> pageBean = pagination.UserPaginationData(userService, pageNum, Constant.PAGE_SIZE_MAIN, keyWords, status);
        if (CommonUtils.isEmpty(pageBean)){
            return new ResultInfo(false);
        }
        return pageBean;
    }

    /**
     * 根据mid查询保修单
     * @param mid
     * @return
     */
    @RequestMapping(value = "/findMainInfo")
    @ResponseBody
    public Object findMainInfo(int mid){
        return userService.findMaintenanceByMid(mid);
    }

    /**
     * 查询维修人员
     * @param rp
     * @return
     */
    @RequestMapping(value = "/findMaintainer")
    @ResponseBody
    public Object findMaintainer(String rp){
        List<Maintainer> list = adminService.findMaintainer(rp,Constant.NOT_BUSY);
        return list;
    }

    /**
     * 管理员指派维修
     * 1. 指派维修工人  维修单指定维修人员 已派工 维修人员由空闲变为在忙
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Object updateStatus(int mId, int manId, String name){
        return adminService.updateStatus(mId, manId, name, StatusEnum.PROCESS.getLabel());
    }

}
