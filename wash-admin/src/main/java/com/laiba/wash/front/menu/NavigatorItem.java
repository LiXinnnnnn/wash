/*
 * Copyright 2010 sdo.com, Inc. All rights reserved.
 * sdo.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : xurong.raddle
 * create time : 2011-6-22 下午02:06:24
 */
package com.laiba.wash.front.menu;

/**
 * 类描述：导航按钮
 * @author patrick.cui
 */
public class NavigatorItem {
    private String name;
    private String hrefUrl;
    private boolean last;

    public NavigatorItem(String name, String hrefUrl) {
        this.name = name;
        this.hrefUrl = hrefUrl;
    }

    public NavigatorItem(String name) {
        this.name = name;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }
}
