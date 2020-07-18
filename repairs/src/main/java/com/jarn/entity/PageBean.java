package com.jarn.entity;

import java.util.List;

/**
 * 数据分页
 * @param <T>
 */
public class PageBean<T> {
    /**
     *页码  ...  第几页
     */
    private int pageNum;
    /**
     *页面大小 ... 一页多少数据
     */
    private int pageSize;
    /**
     *总数  ...多少条数据
     */
    private int total;
    /**
     *总页数
     */
    private int pages;
    /**
     * 查询数据
     */
    private List<T> totalData;

    public PageBean() {
    }

    public PageBean(int pageNum, int pageSize, int total, int pages, List<T> totalData) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.totalData = totalData;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getTotalData() {
        return totalData;
    }

    public void setTotalData(List<T> totalData) {
        this.totalData = totalData;
    }
}
