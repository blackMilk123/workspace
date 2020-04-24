package com.sunrise.dao;

import com.sunrise.model.VideoDeviceInfo;

import java.util.List;
import java.util.Map;

/**
 * 视频监控设备信息表 数据访问
 * video_deviceinfo
 */
public interface VideoDeviceInfoMapper extends com.sunrise.common.BaseMapper<VideoDeviceInfo> {

    List<VideoDeviceInfo> getVideoList(Map<String, Object> param);

    /**
     *按机构group by监控
     * @param param
     * @return
     */
    List<VideoDeviceInfo> selectVideoListByType(Map<String, Object> param);
}
