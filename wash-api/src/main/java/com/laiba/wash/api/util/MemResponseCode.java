package com.laiba.wash.api.util;

public interface MemResponseCode {
	
	/**
	 * 成功
	 */
	public static final String SC_OK = "0";
	
	/**
	 * 业务类型错误     M开头
	 */
	public static final String M_NO_MERCHANT_FIND = "M101"; //没有查询到商户（规则）信息
	public static final String M_MOBILE_ON_USED = "M102";//当前手机号被占用
	public static final String M_CARDNO_ON_USED = "M103";//当前卡号被占用
	public static final String M_NO_MEMBER_FIND = "M104";//没有查询到会员信息
	public static final String M_BALANCE_NOT_ENOUGH = "M105";//会员储值不足
	public static final String M_POINTS_NOT_ENOUGH = "M106";//会员积分不足
	public static final String M_NO_DEFAULT_PINTSATE = "M107";//没有查询到商户的默认积分系数
	public static final String M_NO_DEFAULT_PINTSUSESRATE = "M108";//没有查询到商户的默认积分使用系数
	public static final String M_NO_DEFAULT_MAXPINTSUSESRATE = "M109";//没有查询到商户的积分使用上限
	public static final String M_NO_DEFAULT_BALANCERATE = "M110";//没有查询到商户的默认储值系数
	public static final String M_NO_DEFAULT_SALERATE = "M111";//没有查询到商户的默认打折系数
	public static final String M_LEVELNAME_ON_USED = "M112";//当前商户下级别名称被占用
	public static final String M_NO_LEVEL_FIND = "M113";//会员级别不存在
	public static final String M_NO_BALANCERULE_FIND = "M114";//储值规则不存在
	public static final String M_BALANCERULE_EXITS = "M115";//储值规则重复设置
	public static final String M_NO_TRADEORDER = "M116";//查询不到原交易
	public static final String M_IS_FREEZED = "M117";//会员被冻结，无法完成交易
	public static final String M_IS_DEFAULT_LEVE = "M118";//默认级别不允许删除
	public static final String M_NO_MEM_POINTS = "M119";//查询不到积分账户
	public static final String M_NO_MEM_BALANCE = "M120";//查询不到储值账户
	public static final String M_UNABLE_DELETE_LEVEL = "M121";//会员级别不允许删除
	
	/**
	 * 接口调试类型  T开头
	 */
	public static final String T_UNKNOW_ERROR = "T451";//JSON格式错误
	public static final String T_JSON_ERROR = "T452";//JSON格式错误
	public static final String T_REG_ERROR = "T453";//鉴权错误
	public static final String T_APPID_ERROR = "T454";// 格式错误
	public static final String T_VERSION_ERROR = "T455";//VERSION 格式错误
	public static final String T_CARDNO_ERROR = "T456";//CARDNO会员卡号格式错误
	public static final String T_CUSTID_ERROR = "T457";//CUSTID 格式错误
	public static final String T_MOBILE_ERROR = "T458";//MOBILE会员手机号格式错误
	public static final String T_MEMBERBIRTHDAY_ERROR = "T459";//MEMBERBIRTHDAY会员生日格式错误
	public static final String T_LEVELID_ERROR = "T460";//LEVELID 会员级别ID格式错误
	public static final String T_MEMBERSEX_ERROR = "T461";//MEMBERSEX 会员性别格式错误
	public static final String T_COMMENTS_ERROR = "T462";//COMMENTS 会员描述格式错误
	public static final String T_MEMBERID_ERROR = "T463";//MEMBERID 账户编号格式错误
	public static final String T_FREEZEREMARK_ERROR = "T464";//FREEZEREMARK 冻结/解冻备注格式错误
	public static final String T_FREEZESTATUS_ERROR = "T465";//FREEZESTATUS 账户状态格式错误
	public static final String T_BALANCERULEID_ERROR = "T466";//BALANCERULEID 储值规则编号格式错误
	public static final String T_CLIENTTRADENO_ERROR = "T467";//CLIENTTRADENO交易编号格式错误
	public static final String T_POINTS_ERROR = "T469";//POINTS积分格式错误
	public static final String T_ACCOUNTVALUE_ERROR = "T470";//ACCOUNTVALUE抵扣储值格式错误
	public static final String T_TRANSTIME_ERROR = "T471";//TRANSTIME交易时间格式错误
	public static final String T_INITBALANCE_ERROR = "T472";//INITBALANCE初始储值格式错误
	public static final String T_GIVEBALANCE_ERROR = "T473";//GIVEBALANCE赠送储值格式错误
	public static final String T_NAME_ERROR = "T474";//NAME会员级别名称格式错误
	public static final String T_INITPOINTS_ERROR = "T475";//INITPOINTS初始积分格式错误
	public static final String T_POINTSRATE_ERROR = "T476";//POINTSRATE积分获赠系数格式错误
	public static final String T_SALERATE = "T477";//SALERATE折扣系数格式错误
	public static final String T_SALERATE_ERROR = "T478";//MAXPOINTSRATE最大积分抵扣系数格式错误
	public static final String T_DEFAULTFLAG_ERROR = "T479";//DEFAULTFLAG默认标记格式错误
	public static final String T_MERCHANTRULEID_ERROR  = "T480";//MERCHANTRULEID商户规则编号格式错误
	public static final String T_POINTSUSERATE_ERROR  = "T481";//POINTSUSERATE积分使用系数格式错误
	public static final String T_RECGIVEPOINTSFLAG_ERROR = "T482";//RECGIVEPOINTSFLAG格式错误
	public static final String T_PAGEINDEX_ERROR = "T483";//pageIndex格式错误
	public static final String T_PAGESIZE_ERROR = "T484";//pageSize格式错误
	public static final String T_MEMBERKEY_ERROR = "T485";//MEMBERKEY格式错误
	public static final String T_ORIGINALFEE_ERROR = "T486";//ORIGINALFEE格式错误
	public static final String T_BALANCERATE_ERROR = "T487";//BALANCERATE格式错误
	public static final String T_MAXPOINTSRATE_ERROR = "T488";//最大积分抵扣系数
	public static final String T_OPERATOR_ERROR = "T489";//操作人员
	public static final String T_STARTTIME_ERROR = "T490";//
	public static final String T_ENDTIME_ERROR = "T491";//
	public static final String T_TERMID_ERROR = "T492";//termId错误
	public static final String T_SORT_FILED = "T493";//sortFiled错误
	public static final String T_SORT_TYPE = "T494";//sortType错误
	public static final String T_QUERY_TYPE = "T495";//queryType错误
	public static final String T_GIVE_TYPE = "T496";//giveType错误 
	public static final String T_ACTIVITYFEE_ERROR = "T497";
}
