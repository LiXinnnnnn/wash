package com.laiba.wash.core.po;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.open_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.wx_name
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private String wxName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.wx_img
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private String wxImg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.mobile
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.create_time
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.sex
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Integer sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.province
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private String province;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.city
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.open_id
     *
     * @return the value of user.open_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.open_id
     *
     * @param openId the value for user.open_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.wx_name
     *
     * @return the value of user.wx_name
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public String getWxName() {
        return wxName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.wx_name
     *
     * @param wxName the value for user.wx_name
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setWxName(String wxName) {
        this.wxName = wxName == null ? null : wxName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.wx_img
     *
     * @return the value of user.wx_img
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public String getWxImg() {
        return wxImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.wx_img
     *
     * @param wxImg the value for user.wx_img
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setWxImg(String wxImg) {
        this.wxImg = wxImg == null ? null : wxImg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.mobile
     *
     * @return the value of user.mobile
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.mobile
     *
     * @param mobile the value for user.mobile
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.create_time
     *
     * @return the value of user.create_time
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.create_time
     *
     * @param createTime the value for user.create_time
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.sex
     *
     * @return the value of user.sex
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.sex
     *
     * @param sex the value for user.sex
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.province
     *
     * @return the value of user.province
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.province
     *
     * @param province the value for user.province
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.city
     *
     * @return the value of user.city
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.city
     *
     * @param city the value for user.city
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
}