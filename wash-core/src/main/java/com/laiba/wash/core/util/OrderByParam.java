package com.laiba.wash.core.util;

public class OrderByParam {
    private Object param;
    private OrderBy orderByObject = new OrderBy();

    public OrderBy addOrderBy(String name, boolean asc) {
        return orderByObject.addOrderBy(name, asc);
    }

    /**
     * 返回orderBy
     * 
     * @return ""或orderBy语句
     */
    public String getOrderBy() {
        return orderByObject.getOrderBy();
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public OrderBy getOrderByObject() {
        return orderByObject;
    }

    public void setOrderByObject(OrderBy orderByObject) {
        this.orderByObject = orderByObject;
    }
}
