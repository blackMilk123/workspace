package com.sunrise.service;

import com.sunrise.model.VideoAppInfo;
import com.zhangzlyuyx.fastssm.common.Return;

/**
 * 视频监控应用信息 服务接口
 * video_appinfo
 */
public interface VideoAppInfoService extends com.sunrise.common.BaseService<VideoAppInfo> {

	/**
	 * 更新 accessToken
	 * @param appId 应用id
	 * @return
	 */
	Return<VideoAppInfo> updateVideoAppInfoToken(Long appId);
}
