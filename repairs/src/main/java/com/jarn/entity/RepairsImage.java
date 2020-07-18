package com.jarn.entity;

/**
 * 图片表
 */
public class RepairsImage {

    /**
     * 主键
     */
    private Integer imgId;
    /**
     * 对应报修单
     */
    private Integer mId;
    /**
     * 图片路径
     */
    private String url;

    public RepairsImage() {
    }

    public RepairsImage(Integer imgId, Integer mId, String url) {
        this.imgId = imgId;
        this.mId = mId;
        this.url = url;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
