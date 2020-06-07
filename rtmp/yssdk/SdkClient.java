package com.sunrise.yssdk;

import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import cn.hutool.http.HttpUtil;

/**
 * Sdk 客户端
 * @author zhangzhilong
 *
 */
public class SdkClient {

	/** 获取accessToken */
	private static final String API_TOKEN_GET_URL = "https://open.ys7.com/api/lapp/token/get";
	
	/** 添加设备 */
	private static final String API_DEVICE_ADD_URL = "https://open.ys7.com/api/lapp/device/add";
	
	/** 查询账号下流量消耗汇总 */
	private static final String API_TRAFFIC_TOTAL = "https://open.ys7.com/api/lapp/traffic/user/total";
	
	/** 获取设备列表 */
	private static final String API_DEVICE_LIST = "https://open.ys7.com/api/lapp/device/list";
	
	/** 获取摄像头列表 */
	private static final String API_CAMERA_LIST = "https://open.ys7.com/api/lapp/camera/list";
	
	/** 添加设备 */
	private static final String API_DEVICE_ADD = "https://open.ys7.com/api/lapp/device/add";
	
	/** 获取单个设备信息 */
	private static final String API_DEVICE_INFO = "https://open.ys7.com/api/lapp/device/info";
	
	/** 开始云台控制 */
	private static final String API_DEVICE_PTZ_START = "https://open.ys7.com/api/lapp/device/ptz/start";
	
	/** 停止云台控制 */
	private static final String API_DEVICE_PTZ_STOP = "https://open.ys7.com/api/lapp/device/ptz/stop";
	
	/** 关闭设备视频加密 */
	private static final String API_DEVICE_ENCRYPT_OFF = "https://open.ys7.com/api/lapp/device/encrypt/off";
	
	/** 开启设备视频加密 */
	private static final String API_DEVICE_ENCRYPT_ON = "https://open.ys7.com/api/lapp/device/encrypt/on";
	
	/** 开通直播功能 */
	private static final String API_LIVE_VIDEO_OPEN = "https://open.ys7.com/api/lapp/live/video/open";
	
	/** 关闭直播功能 */
	private static final String API_LIVE_VIDEO_CLOSE = "https://open.ys7.com/api/lapp/live/video/close";
	
	/** 获取指定有效期的直播地址 */
	private static final String API_LIVE_ADDRESS_LIMITED = "https://open.ys7.com/api/lapp/live/address/limited";
	
	/** 获取直播地址*/
	private static final String API_LIVE_ADDRESS_GET = "https://open.ys7.com/api/lapp/live/address/get";
	
	/**
	 * app 标识
	 */
	private String appKey;
	
	public String getAppKey() {
		return appKey;
	}
	
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	/**
	 * app 密钥
	 */
	private String appSecret;
	
	public String getAppSecret() {
		return appSecret;
	}
	
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	/**
	 * token 凭据
	 */
	private String accessToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	/**
	 * token 有效时间
	 */
	private Date tokenExpire;
	
	public Date getTokenExpire() {
		return tokenExpire;
	}
	
	public void setTokenExpire(Date tokenExpire) {
		this.tokenExpire = tokenExpire;
	}
	
	public SdkToken getToken() {
		return getToken(false);
	}
	
	/**
	 * 获取 accessToken
	 * @param force 是否强制获取
	 * @return
	 */
	public SdkToken getToken(boolean force) {
		if(!force && this.getAccessToken() != null) {
			//准备缓存 accessToken
			SdkToken existsSdkToken = new SdkToken("200", "使用 accessToken 缓存", this.getAccessToken(), this.getTokenExpire() != null ? String.valueOf(this.getTokenExpire().getTime() ): "0");
			//判断 token是否过期
			if(this.getTokenExpire() != null && this.getTokenExpire().getTime() < new Date().getTime()) {
				return existsSdkToken;
			}
			//调用接口，验证 accessToken有效性
			SdkReturn<SdkMap> sdkReturn = this.trafficTotal();
			if(sdkReturn.isSuccess()) {
				return existsSdkToken;
			}
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("appKey", this.appKey);
		paramMap.put("appSecret", this.appSecret);
		SdkToken sdkToken = getMap(API_TOKEN_GET_URL, paramMap, SdkToken.class);
		if(sdkToken.isSuccess()) {
			this.setAccessToken(sdkToken.getAccessToken());
			this.setTokenExpire(new Date(sdkToken.getExpireTime()));
		}
		return sdkToken;
	}
	
	/**
	 * 查询账号下流量消耗汇总
	 * @return
	 */
	public SdkReturnMap trafficTotal() {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		return this.getMap(API_TRAFFIC_TOTAL, paramMap);
	}
	
	/**
	 * 添加设备
	 * @param deviceSerial 序列号
	 * @param validateCode 验证码
	 * @return
	 */
	public SdkReturnMap deviceAdd(String deviceSerial, String validateCode) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("deviceSerial", deviceSerial);
		paramMap.put("validateCode", validateCode);
		SdkReturnMap returnMap = getMap(API_DEVICE_ADD, paramMap);
		//添加容错
		if(!returnMap.isSuccess()) {
			if(returnMap.getCode().equals("20017")) {
				returnMap.setCode("200");
			}else if(returnMap.getCode().equals("20002")) {
				returnMap.setMsg(returnMap.getMsg() + ",设备未注册至萤石云");
			}
		}
		return returnMap;
	}
	
	/**
	 * 获取设备列表
	 * @param pageStart 分页起始页，从0开始
	 * @param pageSize 分页大小，默认为10，最大为50
	 * @return
	 */
	public SdkReturnMapList deviceList(int pageStart, int pageSize) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("pageStart", pageStart);
		paramMap.put("pageSize", pageSize);
		return getMapList(API_DEVICE_LIST, paramMap);
	}
	
	/**
	 * 获取单个设备信息
	 * @param deviceSerial
	 * @return
	 */
	public SdkReturnMap deviceInfo(String deviceSerial) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("deviceSerial", deviceSerial);
		return getMap(API_DEVICE_INFO, paramMap);
	}
	
	/**
	 * 获取设备列表
	 * @param pageStart 分页起始页，从0开始
	 * @param pageSize 分页大小，默认为10，最大为50
	 * @return
	 */
	public SdkReturnMapList cameraList(int pageStart, int pageSize) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("pageStart", pageStart);
		paramMap.put("pageSize", pageSize);
		return getMapList(API_CAMERA_LIST, paramMap);
	}
	
	/**
	 * 开启设备视频加密
	 * @param deviceSerial 设备序列号
	 * @return
	 */
	public SdkReturnMap deviceEncryptOn(String deviceSerial) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("deviceSerial", deviceSerial);
		return getMap(API_DEVICE_ENCRYPT_ON, paramMap);
	}
	
	/**
	 * 关闭设备视频加密
	 * @param deviceSerial 设备序列号
	 * @param validateCode 设备验证码
	 * @return
	 */
	public SdkReturnMap deviceEncryptOff(String deviceSerial, String validateCode) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("deviceSerial", deviceSerial);
		paramMap.put("validateCode", validateCode);
		return getMap(API_DEVICE_ENCRYPT_OFF, paramMap);
	}
	
	/**
	 * 开通直播功能
	 * @param channelNo 通道号
	 * @param deviceSerials 设备序列号集合
	 * @return
	 */
	public SdkReturnMapList liveVideoOpen(int channelNo, String... deviceSerials) {
		if(deviceSerials == null || deviceSerials.length == 0) {
			return new SdkReturnMapList();
		}
		String source = "";
		for(int i = 0; i < deviceSerials.length; i++) {
			if(i > 0) {
				source += ",";
			}
			source += (deviceSerials[i] + ":" + channelNo);
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("source", source);
		return getMapList(API_LIVE_VIDEO_OPEN, paramMap);
	}
	
	/**
	 * 关闭直播功能
	 * @param channelNo 通道号
	 * @param deviceSerials 设备序列号集合
	 * @return
	 */
	public SdkReturnMapList liveVideoClose(int channelNo, String... deviceSerials) {
		if(deviceSerials == null || deviceSerials.length == 0) {
			return new SdkReturnMapList();
		}
		String source = "";
		for(int i = 0; i < deviceSerials.length; i++) {
			if(i > 0) {
				source += ",";
			}
			source += (deviceSerials[i] + ":" + channelNo);
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("source", source);
		return getMapList(API_LIVE_VIDEO_CLOSE, paramMap);
	}
	
	/**
	 * 获取直播地址
	 * @param channelNo 通道号 默认 1
	 * @param deviceSerials 设备序列号集合
	 * @return
	 */
	public SdkReturnMapList liveVideoGetAddress(int channelNo, String... deviceSerials) {
		if(deviceSerials == null || deviceSerials.length == 0) {
			return new SdkReturnMapList();
		}
		String source = "";
		for(int i = 0; i < deviceSerials.length; i++) {
			if(i > 0) {
				source += ",";
			}
			source += (deviceSerials[i] + ":" + channelNo);
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("source", source);
		return getMapList(API_LIVE_ADDRESS_GET, paramMap);
	}
	
	/**
	 * 获取指定有效期的直播地址
	 * @param deviceSerial 设备序列号
	 * @param channelNo 通道号
	 * @param expireTime 地址过期时间：单位秒数，最大默认62208000（即720天），最小默认300（即5分钟）。
	 * @return
	 */
	public SdkReturnMap liveVideoLimitedAddress(String deviceSerial, int channelNo, Integer expireTime) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("deviceSerial", deviceSerial);
		paramMap.put("channelNo", channelNo);
		if(expireTime != null) {
			paramMap.put("expireTime", expireTime);
		}
		return getMap(API_LIVE_ADDRESS_LIMITED, paramMap);
	}
	
	/**
	 * 开始云台控制
	 * @param deviceSerial deviceSerial 设备序列号
	 * @param channelNo channelNo 通道号
	 * @param direction direction 操作命令：0-上，1-下，2-左，3-右，4-左上，5-左下，6-右上，7-右下，8-放大，9-缩小，10-近焦距，11-远焦距
	 * @return
	 */
	public SdkReturnMap startDevicePtz(String deviceSerial, int channelNo, int direction) {
		return this.startDevicePtz(deviceSerial, channelNo, direction, 0);
	}
	/**
	 * 开始云台控制
	 * @param deviceSerial 设备序列号
	 * @param channelNo 通道号
	 * @param direction 操作命令：0-上，1-下，2-左，3-右，4-左上，5-左下，6-右上，7-右下，8-放大，9-缩小，10-近焦距，11-远焦距
	 * @param speed 云台速度：0-慢，1-适中，2-快
	 * @return
	 */
	public SdkReturnMap startDevicePtz(String deviceSerial, int channelNo, int direction, int speed) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("deviceSerial", deviceSerial);
		paramMap.put("channelNo", channelNo);
		paramMap.put("direction", String.valueOf(direction));
		paramMap.put("speed", String.valueOf(speed));
		SdkReturnMap sdkReturn = getMap(API_DEVICE_PTZ_START, paramMap);
		return sdkReturn;
	}
	
	/**
	 * 停止云台控制
	 * @param deviceSerial 设备序列号
	 * @param channelNo 通道号
	 * @param direction 操作命令：0-上，1-下，2-左，3-右，4-左上，5-左下，6-右上，7-右下，8-放大，9-缩小，10-近焦距，11-远焦距
	 * @return
	 */
	public SdkReturnMap stopDevicePtz(String deviceSerial, int channelNo, int direction) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessToken", this.getAccessToken());
		paramMap.put("deviceSerial", deviceSerial);
		paramMap.put("channelNo", channelNo);
		paramMap.put("direction", String.valueOf(direction));
		SdkReturnMap sdkReturn = getMap(API_DEVICE_PTZ_STOP, paramMap);
		return sdkReturn;
	}
	
	/*
	public static void main(String[] args) {
		SdkClient sdkClient = new SdkClient();
		sdkClient.setAppKey("e82e92e47b1542d1a7ba2003199f5cf2");
		sdkClient.setAppSecret("58ddf728e5e58c321bb18c3e7c8d0aa8");
		sdkClient.setAccessToken("at.3h35yneydi4i9tz75alqgoe90jq8c9yx-6t55llnnkg-17ys1z5-6lttvhphk-");
		sdkClient.getToken();
	}
	*/
	
	/**
	 * 获取 map
	 * @param url
	 * @param paramMap
	 * @return
	 */
	private SdkReturnMap getMap(String url, Map<String, Object> paramMap) {
		return getMap(url, paramMap, SdkReturnMap.class);
	}
	
	/**
	 * 获取 map
	 * @param url
	 * @param paramMap
	 * @param classz
	 * @return
	 */
	private <T extends SdkReturn<SdkMap>> T getMap(String url, Map<String, Object> paramMap, Class<T> classz) {
		System.out.println(url);
		try {
			String result = HttpUtil.post(url, paramMap);
			System.out.println(result);
			T sdkReturn = JSONObject.parseObject(result, classz);
			return sdkReturn;
		} catch (Exception e) {
			T sdkReturn = null;
			try {
				sdkReturn = classz.newInstance();
				sdkReturn.setCode("-1");
				sdkReturn.setMsg(e.getMessage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return sdkReturn;
		}
	}
	
	/**
	 * 获取 map 集合
	 * @param url
	 * @param paramMap
	 * @return
	 */
	private SdkReturnMapList getMapList(String url, Map<String, Object> paramMap) {
		return getMapList(url, paramMap, SdkReturnMapList.class);
	}
	
	/**
	 * 获取 map 集合
	 * @param url
	 * @param paramMap
	 * @param classz
	 * @return
	 */
	private <T extends SdkReturn<SdkMapList>> T getMapList(String url, Map<String, Object> paramMap, Class<T> classz) {
		System.out.println(url);
		try {
			String result = HttpUtil.post(url, paramMap);
			System.out.println(result);
			T sdkReturn = JSONObject.parseObject(result, classz);
			return sdkReturn;
		} catch (Exception e) {
			T sdkReturn = null;
			try {
				sdkReturn = classz.newInstance();
				sdkReturn.setCode("-1");
				sdkReturn.setMsg(e.getMessage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return sdkReturn;
		}
	}
}
