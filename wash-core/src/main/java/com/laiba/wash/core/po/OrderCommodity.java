package com.laiba.wash.core.po;

import java.io.Serializable;
import java.util.Date;

public class OrderCommodity implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_commodity.id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_commodity.order_no
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private String orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_commodity.user_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_commodity.commodity_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Integer commodityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_commodity.number
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Integer number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_commodity.create_time
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_commodity.commodity_name
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private String commodityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_commodity.commodity_price
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private Double commodityPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_commodity.id
     *
     * @return the value of order_commodity.id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_commodity.id
     *
     * @param id the value for order_commodity.id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_commodity.order_no
     *
     * @return the value of order_commodity.order_no
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_commodity.order_no
     *
     * @param orderNo the value for order_commodity.order_no
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_commodity.user_id
     *
     * @return the value of order_commodity.user_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_commodity.user_id
     *
     * @param userId the value for order_commodity.user_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_commodity.commodity_id
     *
     * @return the value of order_commodity.commodity_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Integer getCommodityId() {
        return commodityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_commodity.commodity_id
     *
     * @param commodityId the value for order_commodity.commodity_id
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_commodity.number
     *
     * @return the value of order_commodity.number
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_commodity.number
     *
     * @param number the value for order_commodity.number
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_commodity.create_time
     *
     * @return the value of order_commodity.create_time
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_commodity.create_time
     *
     * @param createTime the value for order_commodity.create_time
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_commodity.commodity_name
     *
     * @return the value of order_commodity.commodity_name
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public String getCommodityName() {
        return commodityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_commodity.commodity_name
     *
     * @param commodityName the value for order_commodity.commodity_name
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_commodity.commodity_price
     *
     * @return the value of order_commodity.commodity_price
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public Double getCommodityPrice() {
        return commodityPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_commodity.commodity_price
     *
     * @param commodityPrice the value for order_commodity.commodity_price
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }
}