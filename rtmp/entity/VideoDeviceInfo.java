package com.sunrise.model;

import javax.persistence.*;

/**
 * 视频监控设备信息表
 * video_deviceinfo
 */
@Table(name = "video_deviceinfo")
public class VideoDeviceInfo implements java.io.Serializable {

    /** [属性] id  bigint 0 [唯一]  [不可空] */
    public static final String property_id = "id";
    /** [列] id  bigint 0 [唯一]  [不可空] */
    public static final String column_id = "id";
    /** [属性] 设备序列号,英文字母需为大写  varchar 150  [不可空] */
    public static final String property_deviceSerial = "deviceSerial";
    /** [列] 设备序列号,英文字母需为大写  varchar 150  [不可空] */
    public static final String column_deviceSerial = "deviceSerial";
    /** [属性] 设备名称  varchar 765   */
    public static final String property_deviceName = "deviceName";
    /** [列] 设备名称  varchar 765   */
    public static final String column_deviceName = "deviceName";
    /** [属性] 设备类型  varchar 765   */
    public static final String property_deviceType = "deviceType";
    /** [列] 设备类型  varchar 765   */
    public static final String column_deviceType = "deviceType";
    /** [属性] 设备版本号  varchar 765   */
    public static final String property_deviceVersion = "deviceVersion";
    /** [列] 设备版本号  varchar 765   */
    public static final String column_deviceVersion = "deviceVersion";
    /** [属性] 设备验证码,六位大写字母  varchar 150   */
    public static final String property_validateCode = "validateCode";
    /** [列] 设备验证码,六位大写字母  varchar 150   */
    public static final String column_validateCode = "validateCode";
    /** [属性] 是否加密  int 0   */
    public static final String property_isEncrypt = "isEncrypt";
    /** [列] 是否加密  int 0   */
    public static final String column_isEncrypt = "isEncrypt";
    /** [属性] 加密密码  varchar 765   */
    public static final String property_encryptPassword = "encryptPassword";
    /** [列] 加密密码  varchar 765   */
    public static final String column_encryptPassword = "encryptPassword";
    /** [属性] 通道号  int 0   */
    public static final String property_channelNo = "channelNo";
    /** [列] 通道号  int 0   */
    public static final String column_channelNo = "channelNo";
    /** [属性] 通道名称  varchar 765   */
    public static final String property_channelName = "channelName";
    /** [列] 通道名称  varchar 765   */
    public static final String column_channelName = "channelName";
    /** [属性] 添加状态(0未添加1已添加2添加失败)  int 0   */
    public static final String property_createStatus = "createStatus";
    /** [列] 添加状态(0未添加1已添加2添加失败)  int 0   */
    public static final String column_createStatus = "createStatus";
    /** [属性] 添加时间  datetime 0   */
    public static final String property_createTime = "createTime";
    /** [列] 添加时间  datetime 0   */
    public static final String column_createTime = "createTime";
    /** [属性] 添加状态消息  varchar 765   */
    public static final String property_createMsg = "createMsg";
    /** [列] 添加状态消息  varchar 765   */
    public static final String column_createMsg = "createMsg";
    /** [属性] 更新状态(0未更新1已更新)  int 0   */
    public static final String property_updateStatus = "updateStatus";
    /** [列] 更新状态(0未更新1已更新)  int 0   */
    public static final String column_updateStatus = "updateStatus";
    /** [属性] 更新时间  datetime 0   */
    public static final String property_updateTime = "updateTime";
    /** [列] 更新时间  datetime 0   */
    public static final String column_updateTime = "updateTime";
    /** [属性] 更新消息  varchar 765   */
    public static final String property_updateMsg = "updateMsg";
    /** [列] 更新消息  varchar 765   */
    public static final String column_updateMsg = "updateMsg";
    /** [属性] 在线状态(0-不在线1在线)  int 0   */
    public static final String property_onlineStatus = "onlineStatus";
    /** [列] 在线状态(0-不在线1在线)  int 0   */
    public static final String column_onlineStatus = "onlineStatus";
    /** [属性] 云存储状态: -2:设备不支持;-1: 未开通;0: 未激活;1: 激活;2: 过期  int 0   */
    public static final String property_cloudStatus = "cloudStatus";
    /** [列] 云存储状态: -2:设备不支持;-1: 未开通;0: 未激活;1: 激活;2: 过期  int 0   */
    public static final String column_cloudStatus = "cloudStatus";
    /** [属性] 应用ID  bigint 0   */
    public static final String property_appId = "appId";
    /** [列] 应用ID  bigint 0   */
    public static final String column_appId = "appId";
    /** [属性] 机构ID  bigint 0   */
    public static final String property_orgId = "orgId";
    /** [列] 机构ID  bigint 0   */
    public static final String column_orgId = "orgId";
    /** [属性] 机构名称  varchar 765   */
    public static final String property_orgName = "orgName";
    /** [列] 机构名称  varchar 765   */
    public static final String column_orgName = "orgName";
    /** [属性] 视频名称  varchar 765   */
    public static final String property_videoName = "videoName";
    /** [列] 视频名称  varchar 765   */
    public static final String column_videoName = "videoName";
    /** [属性] 视频分类(1饮用水质监控视频，2游泳场所监控，3医疗废弃物监控)  int 0   */
    public static final String property_viedoType = "viedoType";
    /** [列] 视频分类(1饮用水质监控视频，2游泳场所监控，3医疗废弃物监控)  int 0   */
    public static final String column_viedoType = "viedoType";
    /** [属性] 视频播放地址  varchar 765   */
    public static final String property_videoUrl = "videoUrl";
    /** [列] 视频播放地址  varchar 765   */
    public static final String column_videoUrl = "videoUrl";
    /** [属性] 百度坐标经度  varchar 150   */
    public static final String property_bdLon = "bdLon";
    /** [列] 百度坐标经度  varchar 150   */
    public static final String column_bdLon = "bdLon";
    /** [属性] 百度坐标纬度  varchar 150   */
    public static final String property_bdLat = "bdLat";
    /** [列] 百度坐标纬度  varchar 150   */
    public static final String column_bdLat = "bdLat";
    /** [属性] 封面图片  varchar 765   */
    public static final String property_picUrl = "picUrl";
    /** [列] 封面图片  varchar 765   */
    public static final String column_picUrl = "picUrl";
    /** [属性] 状态(1启用0停用)  int 0  [不可空] */
    public static final String property_status = "status";
    /** [列] 状态(1启用0停用)  int 0  [不可空] */
    public static final String column_status = "status";
    /** [列] 是否推荐播放(1启用0停用)  int 0  [不可空] */
    public static final String column_isRecommend = "isRecommend";

    /**
     * id
     * 
     * bigint 0 [唯一]  [不可空]
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = column_id)
    private Long id;
    
    /**
     * 设备序列号,英文字母需为大写
     * 
     * varchar 150  [不可空]
     */
    @Column(name = column_deviceSerial)
    private String deviceSerial;
    
    /**
     * 设备名称
     * 
     * varchar 765  
     */
    @Column(name = column_deviceName)
    private String deviceName;
    
    /**
     * 设备类型
     * 
     * varchar 765  
     */
    @Column(name = column_deviceType)
    private String deviceType;
    
    /**
     * 设备版本号
     * 
     * varchar 765  
     */
    @Column(name = column_deviceVersion)
    private String deviceVersion;
    
    /**
     * 设备验证码,六位大写字母
     * 
     * varchar 150  
     */
    @Column(name = column_validateCode)
    private String validateCode;
    
    /**
     * 是否加密
     * 
     * int 0  
     * 默认值:0
     */
    @Column(name = column_isEncrypt)
    private Integer isEncrypt;
    
    /**
     * 加密密码
     * 
     * varchar 765  
     */
    @Column(name = column_encryptPassword)
    private String encryptPassword;
    
    /**
     * 通道号
     * 
     * int 0  
     * 默认值:1
     */
    @Column(name = column_channelNo)
    private Integer channelNo;
    
    /**
     * 通道名称
     * 
     * varchar 765  
     */
    @Column(name = column_channelName)
    private String channelName;
    
    /**
     * 添加状态(0未添加1已添加2添加失败)
     * 
     * int 0  
     * 默认值:0
     */
    @Column(name = column_createStatus)
    private Integer createStatus;
    
    /**
     * 添加时间
     * 
     * datetime 0  
     */
    @Column(name = column_createTime)
    private java.util.Date createTime;
    
    /**
     * 添加状态消息
     * 
     * varchar 765  
     */
    @Column(name = column_createMsg)
    private String createMsg;
    
    /**
     * 更新状态(0未更新1已更新)
     * 
     * int 0  
     * 默认值:0
     */
    @Column(name = column_updateStatus)
    private Integer updateStatus;
    
    /**
     * 更新时间
     * 
     * datetime 0  
     */
    @Column(name = column_updateTime)
    private java.util.Date updateTime;
    
    /**
     * 更新消息
     * 
     * varchar 765  
     */
    @Column(name = column_updateMsg)
    private String updateMsg;
    
    /**
     * 在线状态(0-不在线1在线)
     * 
     * int 0  
     * 默认值:0
     */
    @Column(name = column_onlineStatus)
    private Integer onlineStatus;
    
    /**
     * 云存储状态: -2:设备不支持;-1: 未开通;0: 未激活;1: 激活;2: 过期
     * 
     * int 0  
     */
    @Column(name = column_cloudStatus)
    private Integer cloudStatus;
    
    /**
     * 应用ID
     * 
     * bigint 0  
     * 默认值:1
     */
    @Column(name = column_appId)
    private Long appId;


    @Column(name = column_isRecommend)
    private String isRecommend;

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 机构ID
     * 
     * bigint 0  
     */
    @Column(name = column_orgId)
    private Long orgId;
    
    /**
     * 机构名称
     * 
     * varchar 765  
     */
    @Column(name = column_orgName)
    private String orgName;
    
    /**
     * 视频名称
     * 
     * varchar 765  
     */
    @Column(name = column_videoName)
    private String videoName;
    
    /**
     * 视频分类(1饮用水质监控视频，2游泳场所监控，3医疗废弃物监控)
     * 
     * int 0  
     */
    @Column(name = column_viedoType)
    private Integer viedoType;
    
    /**
     * 视频播放地址
     * 
     * varchar 765  
     */
    @Column(name = column_videoUrl)
    private String videoUrl;
    
    /**
     * 百度坐标经度
     * 
     * varchar 150  
     */
    @Column(name = column_bdLon)
    private String bdLon;
    
    /**
     * 百度坐标纬度
     * 
     * varchar 150  
     */
    @Column(name = column_bdLat)
    private String bdLat;
    
    /**
     * 封面图片
     * 
     * varchar 765  
     */
    @Column(name = column_picUrl)
    private String picUrl;
    
    /**
     * 状态(1启用0停用)
     * 
     * int 0  [不可空]
     * 默认值:1
     */
    @Column(name = column_status)
    private Integer status;
    
    

    /**
     * 获取 id
     */
    public Long getId(){
        return this.id;
    }
    
    /**
     * 设置 id
     */
    public void setId(Long id){
        this.id = id;
    }
    

    /**
     * 获取 设备序列号,英文字母需为大写
     */
    public String getDeviceSerial(){
        return this.deviceSerial;
    }
    
    /**
     * 设置 设备序列号,英文字母需为大写
     */
    public void setDeviceSerial(String deviceSerial){
        this.deviceSerial = deviceSerial;
    }
    

    /**
     * 获取 设备名称
     */
    public String getDeviceName(){
        return this.deviceName;
    }
    
    /**
     * 设置 设备名称
     */
    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }
    

    /**
     * 获取 设备类型
     */
    public String getDeviceType(){
        return this.deviceType;
    }
    
    /**
     * 设置 设备类型
     */
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }
    

    /**
     * 获取 设备版本号
     */
    public String getDeviceVersion(){
        return this.deviceVersion;
    }
    
    /**
     * 设置 设备版本号
     */
    public void setDeviceVersion(String deviceVersion){
        this.deviceVersion = deviceVersion;
    }
    

    /**
     * 获取 设备验证码,六位大写字母
     */
    public String getValidateCode(){
        return this.validateCode;
    }
    
    /**
     * 设置 设备验证码,六位大写字母
     */
    public void setValidateCode(String validateCode){
        this.validateCode = validateCode;
    }
    

    /**
     * 获取 是否加密
     */
    public Integer getIsEncrypt(){
        return this.isEncrypt;
    }
    
    /**
     * 设置 是否加密
     */
    public void setIsEncrypt(Integer isEncrypt){
        this.isEncrypt = isEncrypt;
    }
    

    /**
     * 获取 加密密码
     */
    public String getEncryptPassword(){
        return this.encryptPassword;
    }
    
    /**
     * 设置 加密密码
     */
    public void setEncryptPassword(String encryptPassword){
        this.encryptPassword = encryptPassword;
    }
    

    /**
     * 获取 通道号
     */
    public Integer getChannelNo(){
        return this.channelNo;
    }
    
    /**
     * 设置 通道号
     */
    public void setChannelNo(Integer channelNo){
        this.channelNo = channelNo;
    }
    

    /**
     * 获取 通道名称
     */
    public String getChannelName(){
        return this.channelName;
    }
    
    /**
     * 设置 通道名称
     */
    public void setChannelName(String channelName){
        this.channelName = channelName;
    }
    

    /**
     * 获取 添加状态(0未添加1已添加2添加失败)
     */
    public Integer getCreateStatus(){
        return this.createStatus;
    }
    
    /**
     * 设置 添加状态(0未添加1已添加2添加失败)
     */
    public void setCreateStatus(Integer createStatus){
        this.createStatus = createStatus;
    }
    

    /**
     * 获取 添加时间
     */
    public java.util.Date getCreateTime(){
        return this.createTime;
    }
    
    /**
     * 设置 添加时间
     */
    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }
    

    /**
     * 获取 添加状态消息
     */
    public String getCreateMsg(){
        return this.createMsg;
    }
    
    /**
     * 设置 添加状态消息
     */
    public void setCreateMsg(String createMsg){
        this.createMsg = createMsg;
    }
    

    /**
     * 获取 更新状态(0未更新1已更新)
     */
    public Integer getUpdateStatus(){
        return this.updateStatus;
    }
    
    /**
     * 设置 更新状态(0未更新1已更新)
     */
    public void setUpdateStatus(Integer updateStatus){
        this.updateStatus = updateStatus;
    }
    

    /**
     * 获取 更新时间
     */
    public java.util.Date getUpdateTime(){
        return this.updateTime;
    }
    
    /**
     * 设置 更新时间
     */
    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime;
    }
    

    /**
     * 获取 更新消息
     */
    public String getUpdateMsg(){
        return this.updateMsg;
    }
    
    /**
     * 设置 更新消息
     */
    public void setUpdateMsg(String updateMsg){
        this.updateMsg = updateMsg;
    }
    

    /**
     * 获取 在线状态(0-不在线1在线)
     */
    public Integer getOnlineStatus(){
        return this.onlineStatus;
    }
    
    /**
     * 设置 在线状态(0-不在线1在线)
     */
    public void setOnlineStatus(Integer onlineStatus){
        this.onlineStatus = onlineStatus;
    }
    

    /**
     * 获取 云存储状态: -2:设备不支持;-1: 未开通;0: 未激活;1: 激活;2: 过期
     */
    public Integer getCloudStatus(){
        return this.cloudStatus;
    }
    
    /**
     * 设置 云存储状态: -2:设备不支持;-1: 未开通;0: 未激活;1: 激活;2: 过期
     */
    public void setCloudStatus(Integer cloudStatus){
        this.cloudStatus = cloudStatus;
    }
    

    /**
     * 获取 应用ID
     */
    public Long getAppId(){
        return this.appId;
    }
    
    /**
     * 设置 应用ID
     */
    public void setAppId(Long appId){
        this.appId = appId;
    }
    

    /**
     * 获取 机构ID
     */
    public Long getOrgId(){
        return this.orgId;
    }
    
    /**
     * 设置 机构ID
     */
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
    

    /**
     * 获取 机构名称
     */
    public String getOrgName(){
        return this.orgName;
    }
    
    /**
     * 设置 机构名称
     */
    public void setOrgName(String orgName){
        this.orgName = orgName;
    }
    

    /**
     * 获取 视频名称
     */
    public String getVideoName(){
        return this.videoName;
    }
    
    /**
     * 设置 视频名称
     */
    public void setVideoName(String videoName){
        this.videoName = videoName;
    }
    

    /**
     * 获取 视频分类(1饮用水质监控视频，2游泳场所监控，3医疗废弃物监控)
     */
    public Integer getViedoType(){
        return this.viedoType;
    }
    
    /**
     * 设置 视频分类(1饮用水质监控视频，2游泳场所监控，3医疗废弃物监控)
     */
    public void setViedoType(Integer viedoType){
        this.viedoType = viedoType;
    }
    

    /**
     * 获取 视频播放地址
     */
    public String getVideoUrl(){
        return this.videoUrl;
    }
    
    /**
     * 设置 视频播放地址
     */
    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }
    

    /**
     * 获取 百度坐标经度
     */
    public String getBdLon(){
        return this.bdLon;
    }
    
    /**
     * 设置 百度坐标经度
     */
    public void setBdLon(String bdLon){
        this.bdLon = bdLon;
    }
    

    /**
     * 获取 百度坐标纬度
     */
    public String getBdLat(){
        return this.bdLat;
    }
    
    /**
     * 设置 百度坐标纬度
     */
    public void setBdLat(String bdLat){
        this.bdLat = bdLat;
    }
    

    /**
     * 获取 封面图片
     */
    public String getPicUrl(){
        return this.picUrl;
    }
    
    /**
     * 设置 封面图片
     */
    public void setPicUrl(String picUrl){
        this.picUrl = picUrl;
    }
    

    /**
     * 获取 状态(1启用0停用)
     */
    public Integer getStatus(){
        return this.status;
    }
    
    /**
     * 设置 状态(1启用0停用)
     */
    public void setStatus(Integer status){
        this.status = status;
    }
    
}
