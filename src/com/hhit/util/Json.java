package com.hhit.util;

import java.io.Serializable;

/**
 * 工具类
 * 统一json数据格式
 */
public class Json implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean success = false;
	private String msg="服务器端异常，请联系管理员!";
	private Object object;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}
