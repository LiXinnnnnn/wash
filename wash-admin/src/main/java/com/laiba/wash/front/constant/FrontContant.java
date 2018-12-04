package com.laiba.wash.front.constant;


/**
 * 前端web使用的常量
 * @author patrick
 *
 */
public class FrontContant {
	
	public static final String DOWNLOAD_KEY_ERROR="Sorry, download the public key failed, please contact the administrator";

	public static final String RSA_PUBLIC_KEY_FILE = "adbkey.pub";
	
	public static final String RSA_PRIVATE_KEY_FILE = "adbkey";
	
	public static final String DEVELOPER = "developer";
	
	public static final String NAVIGATOR_LAST_MENU_KEY= "navigator_last_menu";
	
	public static final String NAVIGATOR_KEY = "navigator";
	
	public static final String LEFT_MENU_KEY = "leftmenu";
	
	public static final int PAGE_SIZE = 10;
	
	public static final String SESSION_OPERATOR = "operator";
	
	public static final String SESSION_PATH = "path";
	
	public static final String SESSION_OPERATOR_PERMISSION = "menu";
	
	public static final String DEFAULT_PASSWORD = "888888";
	
	public static final String OPERATOR_URL = "operatorUrl";
	
	public static final Integer MENU_CONTEXT = 0;
	
	public static final String AJAX_URL = "/ajax";
	
	public static final String FILTER_URL = "/operator/login/kaptcha.htm;/index.htm;/operator/login.htm;/operator/noAuthority.htm;/operator/ajax/loginSubmit.htm;"
			+ "/operator/logout.htm;/operator/noAuthority.htm;/static/datepicker/My97DatePicker.htm;/wx/holidayCounpon.htm;/wx//activityResult.htm";
	
	
	/*public static List<SystemPermissionModel> PERMISSIONLIST;
	
	static{
		
		PERMISSIONLIST = new ArrayList<SystemPermissionModel>();
		
		
		PERMISSIONLIST.add(new SystemPermissionModel("用户管理", "用户管理", 3, null,   "icon-user", "1"));
		PERMISSIONLIST.add(new SystemPermissionModel("修改密码", "修改密码", 1, "/operator/updatePasswordPage.htm",  null, "1"));
		PERMISSIONLIST.add(new SystemPermissionModel("用户配置", "用户配置", 2, "/operator/systemOperatorList.htm", null, "1"));
		PERMISSIONLIST.add(new SystemPermissionModel("角色管理", "角色管理", 3, "/role/systemRoleList.htm",  null, "1"));
		PERMISSIONLIST.add(new SystemPermissionModel("功能管理", "功能管理", 4, "/permission/systemPermissionList.htm",  null, "1"));

		
		
	}*/
	
}
