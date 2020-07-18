package com.jarn.common.enums;

public enum RoleEnum {

    USER(1,"用户"),
    ADMIN(2,"管理员"),;

    RoleEnum(int code, String label){
        this.code = code;
        this.label = label;
    }

    public int code;
    public String label;

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
