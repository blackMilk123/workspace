package com.sunrise.service.impl;

import com.sunrise.dao.VideoDeviceInfoMapper;
import com.sunrise.model.VideoAppInfo;
import com.sunrise.model.VideoDeviceInfo;
import com.sunrise.service.VideoDeviceInfoService;
import com.sunrise.yssdk.SdkClient;
import com.sunrise.yssdk.SdkReturnMap;
import com.sunrise.yssdk.SdkReturnMapList;
import com.zhangzlyuyx.fastssm.common.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 视频监控设备信息表 服务接口
 * video_deviceinfo
 */
@Service("videoDeviceInfo")
public class VideoDeviceInfoServiceImpl extends com.sunrise.common.BaseServiceImpl<VideoDeviceInfo> implements VideoDeviceInfoService {
    
    @Autowired
    private VideoDeviceInfoMapper videoDeviceInfoMapper;
    
    public VideoDeviceInfoServiceImpl() {
    
    }
    
    @Override
    public com.sunrise.common.BaseMapper<VideoDeviceInfo> getMapper() {
        return this.videoDeviceInfoMapper;
    }
    
    /**
	 * 更新设备绑定信息
	 * @param videoDeviceInfo 视频监控设备信息
	 * @param videoAppInfo 视频监控app信息
	 * @return
	 */
    @Override
    public Return<VideoDeviceInfo> updateVideoDeviceInfoAdd(VideoDeviceInfo videoDeviceInfo,
    		VideoAppInfo videoAppInfo) {
    	Date now = this.getDate();
    	if(videoDeviceInfo.getCreateStatus() != null && videoDeviceInfo.getCreateStatus().equals(1)) {
    		return new Return<VideoDeviceInfo>(true, "该设备已绑定").setData(videoDeviceInfo);
    	}
    	SdkClient sdkClient = new SdkClient();
		sdkClient.setAppKey(videoAppInfo.getAppKey());
		sdkClient.setAppSecret(videoAppInfo.getAppSecret());
		sdkClient.setAccessToken(videoAppInfo.getAccessToken());
		sdkClient.setTokenExpire(videoAppInfo.getTokenExpire());
		SdkReturnMap retAdd = sdkClient.deviceAdd(videoDeviceInfo.getDeviceSerial(), videoDeviceInfo.getValidateCode());
		if(!retAdd.isSuccess()) {
			videoDeviceInfo.setCreateStatus(2);
			videoDeviceInfo.setCreateMsg(retAdd.getMsg());
			this.updateSelective(videoDeviceInfo);
			return new Return<>(false, retAdd.getMsg());
		}
		videoDeviceInfo.setCreateStatus(1);
		videoDeviceInfo.setCreateMsg(retAdd.getMsg());
		videoDeviceInfo.setCreateTime(now);
		this.updateSelective(videoDeviceInfo);
    	return new Return<VideoDeviceInfo>(true, retAdd.getMsg()).setData(videoDeviceInfo);
    }
    
    /**
     * 更新设备在线信息
     * @param videoDeviceInfo 视频监控设备信息
     * @param videoAppInfo 视频监控app信息
     * @return
     */
    @Override
    public Return<VideoDeviceInfo> updateVideoDeviceInfoOnline(VideoDeviceInfo videoDeviceInfo, VideoAppInfo videoAppInfo) {
    	Date now = this.getDate();
    	if(videoDeviceInfo.getCreateStatus() == null || videoDeviceInfo.getCreateStatus().equals(0)) {
    		return new Return<>(false, "该设备未绑定!");
    	}
    	if(videoDeviceInfo.getCreateStatus().equals(2)) {
    		return new Return<>(false, "该设备绑定失败!");
    	}
    	SdkClient sdkClient = new SdkClient();
		sdkClient.setAppKey(videoAppInfo.getAppKey());
		sdkClient.setAppSecret(videoAppInfo.getAppSecret());
		sdkClient.setAccessToken(videoAppInfo.getAccessToken());
		sdkClient.setTokenExpire(videoAppInfo.getTokenExpire());
		SdkReturnMap retMap = sdkClient.deviceInfo(videoDeviceInfo.getDeviceSerial());
		if(!retMap.isSuccess()) {
			videoDeviceInfo.setUpdateStatus(2);
			videoDeviceInfo.setUpdateTime(now);
			videoDeviceInfo.setUpdateMsg(retMap.getMsg());
			this.updateSelective(videoDeviceInfo);
			return new Return<>(false, retMap.getMsg());
		}
		//关闭设备视频加密
		SdkReturnMap retOff = sdkClient.deviceEncryptOff(videoDeviceInfo.getDeviceSerial(), videoDeviceInfo.getValidateCode());
		if(retOff.isSuccess()) {
			videoDeviceInfo.setIsEncrypt(0);
		}
		//开启直播
		SdkReturnMapList retLive = sdkClient.liveVideoOpen(1, videoDeviceInfo.getDeviceSerial());
		if(retLive.isSuccess()) {
			//获取直播地址
			SdkReturnMap retLiveAddr = sdkClient.liveVideoLimitedAddress(videoDeviceInfo.getDeviceSerial(), 1, null);
			if(retLiveAddr.isSuccess()) {
				videoDeviceInfo.setVideoUrl(retLiveAddr.getData().getString("rtmpHd"));
			}
		}
		videoDeviceInfo.setDeviceName(retMap.getData().getString("deviceName"));
		videoDeviceInfo.setOnlineStatus(retMap.getData().getInteger("status"));
		videoDeviceInfo.setUpdateStatus(1);
		videoDeviceInfo.setUpdateTime(now);
		videoDeviceInfo.setUpdateMsg(retMap.getMsg());
		this.updateSelective(videoDeviceInfo);
    	return new Return<VideoDeviceInfo>(true, retMap.getMsg()).setData(videoDeviceInfo);
    }

	@Override
	public List<VideoDeviceInfo> getVideoList(Map<String, Object> param) {
		List<VideoDeviceInfo> videoList = videoDeviceInfoMapper.getVideoList(param);
		return videoList;
	}

    @Override
    public List<VideoDeviceInfo> selectVideoListByType(Map<String, Object> param) {
        return videoDeviceInfoMapper.selectVideoListByType(param);
    }
}
