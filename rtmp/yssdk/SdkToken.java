package com.sunrise.yssdk;

public class SdkToken extends SdkReturn<SdkMap> {
	
	private static final long serialVersionUID = -1135570595436061299L;
	
	/**
	 * 获取的accessToken
	 * @return
	 */
	public String getAccessToken() {
		return this.getData() != null ? this.getData().getString("accessToken") : null;
	}
	
	/**
	 * 获取的过期时间，精确到毫秒
	 * @return
	 */
	public Long getExpireTime() {
		return this.getData() != null ? this.getData().getLong("expireTime") : null;
	}
	
	/**
	 * 初始化
	 */
	public SdkToken() {
		
	}
	
	/**
	 * 初始化
	 * @param code
	 * @param msg
	 * @param accessToken
	 * @param expireTime
	 */
	public SdkToken(String code, String msg, String accessToken, String expireTime) {
		this.setCode(code);
		this.setMsg(msg);
		if(accessToken != null || expireTime != null) {
			SdkMap data = new SdkMap();
			data.put("accessToken", accessToken);
			data.put("expireTime", expireTime);
			this.setData(data);
		}
	}
}
