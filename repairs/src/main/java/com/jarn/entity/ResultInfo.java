package com.jarn.entity;

import java.io.Serializable;

public class ResultInfo implements Serializable {
    /**
     * 后端返回结果正常为true,发生异常为false
     */
    private boolean flag = true;
    /**
     * 后端返回结果数据对象
     */
    private Object data;
    /**
     * 发生一次的错误信息
     */
    private String errorMsg;

    public ResultInfo(){}

    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}
