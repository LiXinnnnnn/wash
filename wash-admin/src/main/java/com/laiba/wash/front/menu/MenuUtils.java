package com.laiba.wash.front.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @author patrick
 *
 */
@SuppressWarnings("unchecked")
public class MenuUtils {
    private static PathMatcher                  pathMatcher  = new AntPathMatcher();
    private static Pattern                      paramPattern = Pattern.compile("(\\w+)=(\\w+)");
    private static XStream                      xstream      = new XStream(new DomDriver());
    //官网改版增加的左侧菜单
    private static List<MenuItem>				leftMenus	 = null;

    static {
        xstream.alias("menus", ArrayList.class);
        xstream.alias("menu", MenuItem.class);
        xstream.addImplicitCollection(MenuItem.class, "children");
        xstream.useAttributeFor("id", String.class);
        xstream.useAttributeFor("name", String.class);
        xstream.useAttributeFor("hrefUrl", String.class);
        xstream.useAttributeFor("selectedUrl", String.class);
        xstream.useAttributeFor("target", String.class);
        xstream.useAttributeFor("iconId", String.class);//图标的id 样式  add by summer
        leftMenus = (List<MenuItem>) xstream.fromXML(MenuUtils.class.getResourceAsStream("/leftMenus.xml"));
    }

    public static List<MenuItem> getLeftMenus(HttpSession session){
    	List<MenuItem> cloneChildren = new ArrayList<MenuItem>(leftMenus.size());
        for (MenuItem child : leftMenus) {
        	if ("operator".equals(child.getId())) {
				MenuItem childCloneItem = child.clone();
				List<MenuItem> cloneChildList = childCloneItem.getChildren();
				List<MenuItem> copyChildList = new ArrayList<MenuItem>();
				for (MenuItem cloneChild : cloneChildList) {
					
						copyChildList.add(cloneChild);
					
				}
				childCloneItem.setChildren(copyChildList);
				cloneChildren.add(childCloneItem);
			}else {
				cloneChildren.add(child.clone());
			}
        }
        return cloneChildren;
    }

    public static boolean isSelected(HttpServletRequest request, MenuItem item) {
        String uri = request.getServletPath();
        boolean matched = isMatched(request, uri, item.getHrefUrl(), item.getSelectedUrl());
        if (!matched) {
            matched = isChildrenMatched(request, uri, item);
        }
        return matched;
    }


    public static boolean isChildrenMatched(HttpServletRequest request, String uri, MenuItem parent) {
        if (parent.getChildren() != null && parent.getChildren().size() > 0) {
            for (MenuItem item : parent.getChildren()) {
                if (isMatched(request, uri, item.getHrefUrl(), item.getSelectedUrl())) {
                    return true;
                } else if (isChildrenMatched(request, uri, item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMatched(HttpServletRequest request, String uri, String hrefUrl,
                                    String selectedUrl) {
        // 所有链接
        Set<String> urlSet = new HashSet<String>();
        // 当前链接
        if (StringUtils.isNotBlank(hrefUrl) && !"#".equals(hrefUrl)) {
            urlSet.add(hrefUrl);
        }
        // 扩展的链接
        if (!StringUtils.isBlank(selectedUrl)) {        	
        	String[] urls = StringUtils.split(selectedUrl, ",");
        	if (urls != null) {
        		for (String s : urls) {
        			if (StringUtils.isNotBlank(s) && !"#".equals(s)) {
        				urlSet.add(s);
        			}
        		}
        	}
        }
        for (String urlPattern : urlSet) {
            // 判断是否带参数验证
            if (urlPattern.indexOf("?") != -1) {
                String[] patterns = StringUtils.split(urlPattern, "?");
                // uri验证
                boolean urlMatched = pathMatcher.match(patterns[0], uri);
                if (urlMatched) {
                    // 参数验证
                    boolean paramMatched = true;
                    Matcher matcher = paramPattern.matcher(patterns[1]);
                    while (matcher.find()) {
                        if (!StringUtils.equals(matcher.group(2),
                            request.getParameter(matcher.group(1)))) {
                            paramMatched = false;
                            break;
                        }
                    }
                    if (paramMatched) {
                        return true;
                    }
                }
            } else if (pathMatcher.match(urlPattern, uri)) {
                return true;
            }
        }
        return false;
    }

    public static Navigator generateNavigator(HttpServletRequest request) {
        Navigator navigator = new Navigator();
        for (MenuItem item : leftMenus) {
            if (isSelected(request, item)) {
                navigator.addItem(item.getName(), item.getHrefUrl());
                // 子节点
                MenuItem parent = item;
                while (parent.getChildren() != null) {
                    boolean hasMatched = false;
                    for (MenuItem child : parent.getChildren()) {
                        if (isSelected(request, child)) {
                            navigator.addItem(child.getName(), child.getHrefUrl());
                            parent = child;
                            hasMatched = true;
                            break;
                        }
                    }
                    // 没有匹配的，跳出循环，防止死循环
                    if (!hasMatched) {
                        break;
                    }
                }
                break;
            }
        }
        return navigator;
    }
}
