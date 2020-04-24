package com.sunrise.service.impl;

import com.sunrise.dao.VideoAppInfoMapper;
import com.sunrise.model.VideoAppInfo;
import com.sunrise.service.VideoAppInfoService;
import com.sunrise.yssdk.SdkClient;
import com.sunrise.yssdk.SdkToken;
import com.zhangzlyuyx.fastssm.common.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 视频监控应用信息 服务接口
 * video_appinfo
 */
@Service("videoAppInfo")
public class VideoAppInfoServiceImpl extends com.sunrise.common.BaseServiceImpl<VideoAppInfo> implements VideoAppInfoService {
    
    @Autowired
    private VideoAppInfoMapper videoAppInfoMapper;
    
    public VideoAppInfoServiceImpl() {
    
    }
    
    @Override
    public com.sunrise.common.BaseMapper<VideoAppInfo> getMapper() {
        return this.videoAppInfoMapper;
    }
    
    @Override
    public Return<VideoAppInfo> updateVideoAppInfoToken(Long appId) {
    	//查询视频监控应用信息
    	VideoAppInfo videoAppInfo = this.selectByPrimaryKey(appId);
    	if(videoAppInfo == null) {
    		return new Return<>(false, "获取视频监控app信息失败!");
    	}
    	//accessToken刷新
    	SdkClient sdkClient = new SdkClient();
    	sdkClient.setAppKey(videoAppInfo.getAppKey());
    	sdkClient.setAppSecret(videoAppInfo.getAppSecret());
    	sdkClient.setAccessToken(videoAppInfo.getAccessToken());
    	sdkClient.setTokenExpire(videoAppInfo.getTokenExpire());
    	SdkToken sdkToken = sdkClient.getToken();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		//设置过期时间为当前时间+7天
		calendar.add(Calendar.DATE,7);
		String format = dateFormat.format(calendar.getTime());
		//更新过期时间
		videoAppInfo.setAccessToken(sdkToken.getAccessToken());
		videoAppInfo.setTokenExpire(calendar.getTime());
		videoAppInfo.setRemark(sdkToken.getMsg());
		this.updateSelective(videoAppInfo);

		if(!sdkToken.isSuccess()) {
    		videoAppInfo.setRemark(sdkToken.getMsg());
    		this.updateSelective(videoAppInfo);
    		return new Return<>(false, sdkToken.getMsg());
    	}
    	//如果accessToken一致，则数据不变
    	if(videoAppInfo.getAccessToken() != null && videoAppInfo.getAccessToken().equalsIgnoreCase(sdkToken.getAccessToken())) {
    		return new Return<VideoAppInfo>(true, "accessToken 无变化").setData(videoAppInfo);
    	}
    	//更新 accessToken
    	videoAppInfo.setAccessToken(sdkToken.getAccessToken());
		videoAppInfo.setTokenExpire(new Date(sdkToken.getExpireTime()));
    	videoAppInfo.setRemark(sdkToken.getMsg());
    	this.updateSelective(videoAppInfo);
    	//返回结果
    	return new Return<VideoAppInfo>(true, "已更新 accessToken").setData(videoAppInfo);
    }
}
