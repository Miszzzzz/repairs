package com.jarn.entity;

import java.io.Serializable;

/**
 * 角色表
 */
public class Role implements Serializable {

    /**
     * 角色id
     */
    private int rid;
    /**
     * 角色 --> 用户、管理员、维修工人
     */
    private String r;

    public Role() {
    }

    public Role(int rid, String r) {
        this.rid = rid;
        this.r = r;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }
}
