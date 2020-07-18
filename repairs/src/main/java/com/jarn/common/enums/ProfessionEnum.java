package com.jarn.common.enums;

public enum ProfessionEnum {

    WE("WaterElectricity","水电类维修"),
    C("Civil","土工类维修"),
    HT("HotWater","热水系统维修"),
    DW("DrinkingWater","直饮水系统维修"),
    WM("WashingMachine","洗衣机维修"),
    AC("AirConditioning","空调维修"),;

    String code;
    String label;

    ProfessionEnum(String code, String label) {
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
