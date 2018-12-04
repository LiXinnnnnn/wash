/*
 * Copyright 2010 sdo.com, Inc. All rights reserved.
 * sdo.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : xurong.raddle
 * create time : 2011-6-22 下午02:05:01
 */
package com.laiba.wash.front.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：页面导航栏（面包屑）
 * @author patrick.cui
 */
public class Navigator {
    public final static String NAVIGATOR_KEY = "navigator";
    private List<NavigatorItem> items = new ArrayList<NavigatorItem>();

    public void addItem(String name, String hrefUrl) {
        if (items.size() > 0) {
            items.get(items.size() - 1).setLast(false);
        }
        NavigatorItem newItem = new NavigatorItem(name, hrefUrl);
        newItem.setLast(true);
        items.add(newItem);
    }

    public void addItem(String name) {
        addItem(name, null);
    }

    public List<NavigatorItem> getItems() {
        return items;
    }

}
