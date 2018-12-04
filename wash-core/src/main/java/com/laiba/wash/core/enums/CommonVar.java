package com.laiba.wash.core.enums;


/**
 * 常用静态变量
 * @author xumm
 *
 */
public class CommonVar {
	public static final String LOGO = "koolpos";
	
	public static final String SYSTEM_ADMIN_NAME = "liuning";
	public static final Integer SYSTEM_ADMIN_ID = 0;
	
	public static final int CURRENT_PAGE = 1;
	public static final int PAGE_SIZE = 20;
	
	
	public static enum COMMON_STATUS{
		INACTIVITY(0, "无效"), ACTIVITY(1, "有效");
		public Integer value;
		public String valueName;

		COMMON_STATUS(Integer v, String name) {
			this.value = v;
			this.valueName = name;
		}	
	}
	
	/**
	 * 权限类型： 1.功能模块  2.功能组件
	 * @author xumm
	 */
	public static enum SYSTEM_PERMISSION{
		MODEL(1, "功能模块"), PART(2, "功能组件");
		public Integer value;
		public String valueName;

		SYSTEM_PERMISSION(Integer v, String name) {
			this.value = v;
		}		
	} 
	
	/**
	 * 查询type
	 * @author xumm
	 *
	 */
	public static enum SELECT_TYPE{
		EQUALS(1, "精确查询"), LIKE(2, "模糊查询");
		public Integer value;
		public String valueName;

		SELECT_TYPE(Integer v, String name) {
			this.value = v;
			this.valueName = name;
		}	
	}
	
	/**
	 * 文件类型
	 * @author xumm
	 *
	 */
	public static enum FILE_TYPE{
		PIC(1, "图片"), APK(2, "安装包");
		public Integer value;
		public String valueName;

		FILE_TYPE(Integer v, String name) {
			this.value = v;
			this.valueName = name;
		}	
	}
	
}
