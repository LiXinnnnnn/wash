package com.laiba.wash.core.enums;

/**
 * ACTIVITY的value为1，INACTIVITY的value为0
 * @author jerry
 *
 */
public enum StatusEnum {
	
	ACTIVITY(1),INACYIVITY(0);
	
	// 定义变量
    private int value;

    // 构造函数，枚举类型只能为私有
    private StatusEnum(int value) {
        this.value = value;
    }

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
   
}
