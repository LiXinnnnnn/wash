package com.laiba.wash.core.dao.mapper;

import com.laiba.wash.core.po.OrderCommodity;
import com.laiba.wash.core.po.OrderCommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderCommodityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int countByExample(OrderCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int deleteByExample(OrderCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int insert(OrderCommodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int insertSelective(OrderCommodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    List<OrderCommodity> selectByExample(OrderCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    OrderCommodity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int updateByExampleSelective(@Param("record") OrderCommodity record, @Param("example") OrderCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int updateByExample(@Param("record") OrderCommodity record, @Param("example") OrderCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int updateByPrimaryKeySelective(OrderCommodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_commodity
     *
     * @mbggenerated Mon Sep 11 15:20:00 CST 2017
     */
    int updateByPrimaryKey(OrderCommodity record);
}