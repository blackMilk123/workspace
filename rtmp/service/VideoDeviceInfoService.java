package com.sunrise.service;

import com.sunrise.model.VideoAppInfo;
import com.sunrise.model.VideoDeviceInfo;
import com.zhangzlyuyx.fastssm.common.Return;

import java.util.List;
import java.util.Map;

/**
 * 视频监控设备信息表 服务接口
 * video_deviceinfo
 */
public interface VideoDeviceInfoService extends com.sunrise.common.BaseService<VideoDeviceInfo> {

	/**
	 * 更新设备绑定信息
	 * @param videoDeviceInfo 视频监控设备信息
	 * @param videoAppInfo 视频监控app信息
	 * @return
	 */
	Return<VideoDeviceInfo> updateVideoDeviceInfoAdd(VideoDeviceInfo videoDeviceInfo, VideoAppInfo videoAppInfo);
	
	/**
     * 更新设备在线信息
     * @param videoDeviceInfo 视频监控设备信息
     * @param videoAppInfo 视频监控app信息
     * @return
     */
    Return<VideoDeviceInfo> updateVideoDeviceInfoOnline(VideoDeviceInfo videoDeviceInfo, VideoAppInfo videoAppInfo);

    /**
     * @description 查询视频监控
     * @param  
     * @create 2019/12/15 17:15
     * @return 
     */ 
    List<VideoDeviceInfo> getVideoList(Map<String, Object> param);

	/**
	 *按机构group by监控
	 * @param param
	 * @return
	 */
    List<VideoDeviceInfo> selectVideoListByType(Map<String, Object> param);
}
