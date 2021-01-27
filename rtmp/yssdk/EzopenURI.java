package com.sunrise.yssdk;

/**
 * ezopen 协议
 *
 */
public class EzopenURI {

	/**
	 * 协议头
	 */
	private String scheme = "ezopen";

	public String getScheme() {
		return this.scheme;
	}

	/**
	 * 服务器地址
	 */
	private String host = "open.ys7.com";

	public String getHost() {
		return this.host;
	}

	/**
	 * 设备序列号
	 */
	private String deviceSerial;

	public String getDeviceSerial() {
		return this.deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	/**
	 * 设备验证码
	 */
	private String validateCode;

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	/**
	 * 通道号
	 */
	private String channelNo = "1";

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	/**
	 * 静音模式
	 */
	private boolean mute = false;

	public boolean getMute() {
		return this.mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	/**
	 * 初始化
	 */
	public EzopenURI() {

	}

	/**
	 * 初始化
	 * 
	 * @param deviceSerial
	 * @param validateCode
	 */
	public EzopenURI(String deviceSerial, String validateCode) {
		this.deviceSerial = deviceSerial;
		this.validateCode = validateCode;
	}

	/**
	 * 获取 URL
	 * 
	 * @return
	 */
	public String getURL() {
		StringBuilder str = new StringBuilder();
		// 协议
		str.append(this.scheme);
		str.append(":");
		str.append("//");
		// 服务器地址
		str.append(this.host);
		str.append("/");
		// 设备序列号
		str.append(this.deviceSerial);
		str.append("/");
		// 通道号
		str.append(this.channelNo);
		return str.toString();
	}

	@Override
	public String toString() {
		return this.getURL();
	}

	/**
	 * 获取实时预览 URL
	 * 
	 * @param hd
	 *            true高清模式,false流畅模式
	 * @param mute
	 *            是否静音模式
	 * @return
	 */
	public String getRealPlayURL(boolean hd, boolean mute) {
		StringBuilder str = new StringBuilder(this.getURL());
		// 高清模式
		if (hd) {
			str.append(".hd");
		}
		// 格式
		str.append(".live");
		// 静音模式
		if (mute) {
			str.append("?mute=" + String.valueOf(mute));
		}
		return str.toString();
	}

	/**
	 * 获取回放预览 URL==
	 * 
	 * @param backSrc
	 *            回放源, 自动选择（缺省值）、本地存储（local）、云存储（cloud)
	 * @param mute
	 *            是否静音模式
	 * @param begin
	 *            回放开始时间(默认当天开始时间)
	 *            yyyyMMddhhmmss/yyyyMMddhhmm/yyyyMMddhh/yyyyMMdd
	 * @param end
	 *            回放结束时间(默认当天结束时间)
	 *            yyyyMMddhhmmss/yyyyMMddhhmm/yyyyMMddhh/yyyyMMdd,
	 *            无end的时候end=begin的yyyyMMdd235959
	 * @return
	 */
	public String getBackPlayURL(String backSrc, boolean mute, String begin, String end) {
		StringBuilder str = new StringBuilder(this.getURL());
		// hack:回放默认使用云存储
		if (backSrc == null || backSrc.length() == 0) {
			backSrc = "cloud";
		}
		// 回放源
		if (backSrc != null) {
			if (!backSrc.startsWith(".")) {
				backSrc = "." + backSrc;
			}
			str.append(backSrc);
		}
		// 格式
		str.append(".rec");
		int index = 0;
		// 静音模式
		if (mute) {
			str.append(index == 0 ? "?" : "&");
			str.append("mute=" + String.valueOf(mute));
			index++;
		}
		// 回放开始时间
		if (begin != null && begin.length() > 0) {
			str.append(index == 0 ? "?" : "&");
			str.append("begin=" + begin);
			index++;
		}
		// 回放结束时间
		if (end != null && end.length() > 0) {
			str.append(index == 0 ? "?" : "&");
			str.append("end=" + end);
			index++;
		}
		return str.toString();
	}
}
