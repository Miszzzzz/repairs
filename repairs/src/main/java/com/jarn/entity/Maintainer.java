package com.jarn.entity;

import java.io.Serializable;

/**
 * 维修人员
 */
public class Maintainer implements Serializable {
    /**
     * 主键id
     */
    private int manId;
    /**
     * 账户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 职业
     */
    private String profession;
    /**
     * 姓名
     */
    private String name; //姓名
    /**
     * 状态  默认空闲  在忙
     */
    private String status;

    public Maintainer() {
    }

    public Maintainer(int manId, String username, String password, String phone, String profession, String name, String status) {
        this.manId = manId;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.profession = profession;
        this.name = name;
        this.status = status;
    }

    public int getManId() {
        return manId;
    }

    public void setManId(int manId) {
        this.manId = manId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
