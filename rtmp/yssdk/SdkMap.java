package com.sunrise.yssdk;

import java.util.HashMap;
import java.util.Map;

/**
 * Sdk map
 * @author zhangzhilong
 *
 */
public class SdkMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 800578747263153921L;
	
	public String getString(String key) {
		for(Map.Entry<String, Object> kv : super.entrySet()) {
			if(kv.getKey().equalsIgnoreCase(key)) {
				return kv.getValue() == null ? null : kv.getValue().toString();
			}
		}
		return null;
	}
	
	public Long getLong(String key) {
		String value = this.getString(key);
		if(value == null) {
			return null;
		}
		return Long.parseLong(value);
	}
	
	public Integer getInteger(String key) {
		String value = this.getString(key);
		if(value == null) {
			return null;
		}
		return Integer.parseInt(value);
	}
}
