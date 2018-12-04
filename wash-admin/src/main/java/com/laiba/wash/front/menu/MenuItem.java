/**
 *
 */
package com.laiba.wash.front.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 菜单子项
 * @author patrick
 *
 */
@XStreamAlias("MenuItem")
public class MenuItem {
    /**
     * id
     */
    private String         id;
    
    /**
     * id
     */
    private String         iconId;
    /**
     * 菜单名称
     */
    private String         name;
    /**
     * 菜单的链接
     */
    private String         hrefUrl;
    /**
     * 菜单对应的其他链接，用逗号隔开
     */
    private String         selectedUrl;
    /**
     * <a>标签的target属性值，默认为_self
     */
    private String         target;
    /**
     * 是否被选中。
     */
    private boolean        isSelected;

    private List<MenuItem> children = new ArrayList<MenuItem>();


    public MenuItem clone() {
        MenuItem menuItem = new MenuItem();
        BeanUtils.copyProperties(this, menuItem, new String[] { "children" });
        List<MenuItem> cloneChildren = new ArrayList<MenuItem>();
        if (children != null) {
            for (MenuItem child : children) {
                cloneChildren.add(child.clone());
            }
        }
        menuItem.setChildren(cloneChildren);
        return menuItem;
    }


	public String getId() {
		return id;
	}


	public String getIconId() {
		return iconId;
	}


	public void setIconId(String iconId) {
		this.iconId = iconId;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getSelectedUrl() {
		return selectedUrl;
	}


	public void setSelectedUrl(String selectedUrl) {
		this.selectedUrl = selectedUrl;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public boolean isSelected() {
		return isSelected;
	}


	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	
	public List<MenuItem> getChildren() {
		return children;
	}


	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}
}
