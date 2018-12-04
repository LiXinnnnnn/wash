/**
 * 
 */
package com.laiba.wash.core.util;

/**
 * 排序
 * 
 * @author cuiping.patrick
 * 
 */
public class OrderBy {
    private StringBuilder sb = new StringBuilder();

    /**
     * 增加排序
     * @param name 列名
     * @param asc true正序，false倒序
     * @return 自己
     */
    public OrderBy addOrderBy(String name, boolean asc) {
        if (sb.length() > 0) {
            sb.append(",");
        }
        sb.append(name);
        if (asc) {
            sb.append(" ").append("asc");
        } else {
            sb.append(" ").append("desc");
        }
        return this;
    }

    /**
     * 返回orderBy
     * 
     * @return ""或orderBy语句
     */
    public String getOrderBy() {
        return sb.toString();
    }

    @Override
    public String toString() {
        return getOrderBy();
    }
}
