package com.jarn.common.enums;

public enum StatusEnum {

    ALL("0","全部"),
    NOT("10","未审核"),
    PROCESS("20","已派工"),
    CARRYOUT("30","已完成"),;


    String code;
    String label;
    StatusEnum(String code, String label){
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

}
