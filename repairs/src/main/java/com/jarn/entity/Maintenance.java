package com.jarn.entity;


import com.jarn.common.enums.StatusEnum;
import com.jarn.common.util.RandomUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 报修单
 */
public class Maintenance implements Serializable {

    /**
     * 主键
     */
    private Integer mId;
    /**
     * 报修人姓名
     */
    private String name;
    /**
     * 报修人电话号码
     */
    private String phone;
    /**
     * 报修区域 --> 东院
     */
    private String address;
    /**
     * 详细地址 --> 4栋510
     */
    private String detailAddress;
    /**
     * 报修项目 --> 水电类维修
     */
    private String repairsType;  // 报修项目
    /**
     * 报修详情 --> 具体描述
     */
    private String repairsDetail;
    /**
     * 报修时间 --> 提交报修时间
     */
    private String repairsDate = RandomUtil.getDateStr();
    /**
     * 预约时间
     */
    private String appointmentDate;
    /**
     * 订单状态 --> 未审核、已派工、已完成
     */
    private String status = StatusEnum.NOT.getLabel(); //订单状态
    /**
     * 派工时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 用户评价
     */
    private String evaluation;
    /**
     * 委托维修人员id
     */
    private int manId;
    /**
     * 委托的维修人员姓名
     */
    private String manName;
    /**
     * //提交人员
     */
    private int uid;

    /**
     * 报修上传图片
     */
    private List<RepairsImage> images;

    public Maintenance() {
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getRepairsType() {
        return repairsType;
    }

    public void setRepairsType(String repairsType) {
        this.repairsType = repairsType;
    }

    public String getRepairsDetail() {
        return repairsDetail;
    }

    public void setRepairsDetail(String repairsDetail) {
        this.repairsDetail = repairsDetail;
    }

    public String getRepairsDate() {
        return repairsDate;
    }

    public void setRepairsDate(String repairsDate) {
        this.repairsDate = repairsDate;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public int getManId() {
        return manId;
    }

    public void setManId(int manId) {
        this.manId = manId;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<RepairsImage> getImages() {
        return images;
    }

    public void setImages(List<RepairsImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "mId=" + mId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", repairsType='" + repairsType + '\'' +
                ", repairsDetail='" + repairsDetail + '\'' +
                ", repairsDate='" + repairsDate + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", evaluation='" + evaluation + '\'' +
                ", manId=" + manId +
                ", manName='" + manName + '\'' +
                ", uid=" + uid +
                ", images=" + images +
                '}';
    }
}
