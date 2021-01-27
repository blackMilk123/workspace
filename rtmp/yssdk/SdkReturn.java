package com.sunrise.yssdk;

import java.io.Serializable;

/**
 * sdk 返回结果
 * @author zhangzhilong
 *
 * @param <T>
 */
public class SdkReturn<T> implements Serializable {
	
	private static final long serialVersionUID = 3079118551900894996L;
	
	/**
	 * 返回码
	 */
	private String code;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 返回消息
	 */
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 返回数据
	 */
	private T data;
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public SdkReturn() {
		
	}
	
	public SdkReturn(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	/**
	 * 获取是否成功请求
	 * @return
	 */
	public boolean isSuccess() {
		return this.code != null && this.code.equals("200");
	}
}
