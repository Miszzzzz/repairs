package com.jarn.common.util;

import com.jarn.entity.PageBean;
import com.jarn.service.BaseService;
import java.util.List;

/**
 * @Author WZY
 **/
public class PaginationUtils<T> {
    /**
     * 分页工具类
     * pageNum 页码
     * pageSize 页面大小
     * keyWords 关键字
     * status 保修单状态
     * uid 用户id值 用户查看报修分页数据
     */


    //页码，页面大小，关键字，保修单状态 , 用户uid
    public PageBean<T> basePaginationData(BaseService baseService, int pageNum, int pageSize, String keyWords, String status, int uid){

        //数据总数
        int total = baseService.findTotal(keyWords, status, uid);

        if (total == 0) return null;

        //总页数
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageNum > pages){
            pageNum = pages;
        }

        //下表索引值
        int index = (pageNum - 1) * pageSize;

        //查询数据
        List<T> totalData = baseService.findAll(index, pageSize, keyWords, status, uid);

        PageBean<T> pageBean = new PageBean<>(pageNum, pageSize, total,  pages, totalData);

        return pageBean;
    }

    //可以根据前端页面传入的值 添加关键字和状态 查询语句支持
    //报修订单
    public PageBean<T> UserPaginationData(BaseService baseService, int pageNum, int pageSize, int uid){
        return basePaginationData(baseService, pageNum, pageSize, null, null, uid);
    }

    //可以根据前端页面传入的值 添加关键字和状态 查询语句支持
    // 用户表
    public PageBean<T> UserPaginationData(BaseService baseService,int pageNum, int pageSize){
        return basePaginationData(baseService, pageNum, pageSize, null, null, 0);
    }

    //管理员操作保修单
    public PageBean<T> UserPaginationData(BaseService baseService,int pageNum, int pageSize, String keyWords, String status){
        return basePaginationData(baseService, pageNum, pageSize, keyWords, status, 0);
    }


}
