package com.laiba.wash.front.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.laiba.wash.front.constant.FrontContant;
import com.laiba.wash.front.menu.MenuItem;
import com.laiba.wash.front.menu.MenuUtils;
import com.laiba.wash.front.menu.Navigator;

/**
 * 
 * @author jerry
 *
 */
public class NavigatorInterceptor implements HandlerInterceptor {
	
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
//    	//没有view或者view为blank的时候，不需要设置菜单和导航栏
    	if(modelAndView == null){
    		return;
    	}
    	if(StringUtils.isBlank(modelAndView.getViewName())){
    		return;
    	}
    	if("control/blank".equals(modelAndView.getViewName())){
    		return;
    	}
    	//通过当前request.url来匹配menulist，设置menu的isselected属性
    	List<MenuItem> leftMenus = MenuUtils.getLeftMenus(request.getSession());
    	for(MenuItem menu : leftMenus){
    		if(MenuUtils.isSelected(request, menu)){
    			menu.setSelected(true);
    		}
    		List<MenuItem> childMenus = menu.getChildren();
    		if(childMenus != null && childMenus.size() > 0){
    			for(MenuItem child : childMenus){
    				if(MenuUtils.isSelected(request, child)){
    					child.setSelected(true);
    	    		}
    			}
    		}
    	}
    	modelAndView.getModelMap().put(FrontContant.LEFT_MENU_KEY, leftMenus);
    	//通过当前匹配后的menulist,来自动生成navigator
    	Navigator na = MenuUtils.generateNavigator(request);
    	Object lastMenu = modelAndView.getModelMap().get(FrontContant.NAVIGATOR_LAST_MENU_KEY);
    	if(lastMenu != null){
    		na.addItem((String)lastMenu);
    	}
    	modelAndView.getModelMap().put(FrontContant.NAVIGATOR_KEY, na);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request,
    		HttpServletResponse response, Object handler, Exception ex)
    				throws Exception {
    }

}
