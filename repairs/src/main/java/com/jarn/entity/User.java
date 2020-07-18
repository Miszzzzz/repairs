package com.jarn.entity;

import java.io.Serializable;

/**
 * 用户表
 */
public class User implements Serializable {

    /**
     * 主键id
     */
    private int uid;
    /**
     * 用户账户
     */
    private String username;
    /**
     * 账户密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 职业  维修工人对应--相应职位
     */
    private String profession;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 对应角色
     */
    private Role r ;

    /**
     * 对应角色id
     */
    private int rid;

    public User() {
    }

    public User(Integer uid, String username, String password, String name, String profession, String phone, String email, Role r, int rid) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.profession = profession;
        this.phone = phone;
        this.email = email;
        this.r = r;
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getR() {
        return r;
    }

    public void setR(Role r) {
        this.r = r;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", r=" + r +
                ", rid=" + rid +
                '}';
    }
}
