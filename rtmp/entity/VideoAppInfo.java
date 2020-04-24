package com.sunrise.model;

import javax.persistence.*;

/**
 * 视频监控应用信息
 * video_appinfo
 */
@Table(name = "video_appinfo")
public class VideoAppInfo implements java.io.Serializable {

    /** [属性] id  bigint 0 [唯一]  [不可空] */
    public static final String property_id = "id";
    /** [列] id  bigint 0 [唯一]  [不可空] */
    public static final String column_id = "id";
    /** [属性] 应用名称  varchar 765   */
    public static final String property_appName = "appName";
    /** [列] 应用名称  varchar 765   */
    public static final String column_appName = "appName";
    /** [属性] 应用KEY  varchar 765  [不可空] */
    public static final String property_appKey = "appKey";
    /** [列] 应用KEY  varchar 765  [不可空] */
    public static final String column_appKey = "appKey";
    /** [属性] 应用密钥  varchar 765  [不可空] */
    public static final String property_appSecret = "appSecret";
    /** [列] 应用密钥  varchar 765  [不可空] */
    public static final String column_appSecret = "appSecret";
    /** [属性] 认证凭据  varchar 765   */
    public static final String property_accessToken = "accessToken";
    /** [列] 认证凭据  varchar 765   */
    public static final String column_accessToken = "accessToken";
    /** [属性] 认证有效期  datetime 0   */
    public static final String property_tokenExpire = "tokenExpire";
    /** [列] 认证有效期  datetime 0   */
    public static final String column_tokenExpire = "tokenExpire";
    /** [属性] 备注  varchar 765   */
    public static final String property_remark = "remark";
    /** [列] 备注  varchar 765   */
    public static final String column_remark = "remark";
    /** [属性] 状态(1启用0停用)  int 0   */
    public static final String property_status = "status";
    /** [列] 状态(1启用0停用)  int 0   */
    public static final String column_status = "status";
    
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
     * 应用名称
     * 
     * varchar 765  
     */
    @Column(name = column_appName)
    private String appName;
    
    /**
     * 应用KEY
     * 
     * varchar 765  [不可空]
     */
    @Column(name = column_appKey)
    private String appKey;
    
    /**
     * 应用密钥
     * 
     * varchar 765  [不可空]
     */
    @Column(name = column_appSecret)
    private String appSecret;
    
    /**
     * 认证凭据
     * 
     * varchar 765  
     */
    @Column(name = column_accessToken)
    private String accessToken;
    
    /**
     * 认证有效期
     * 
     * datetime 0  
     */
    @Column(name = column_tokenExpire)
    private java.util.Date tokenExpire;
    
    /**
     * 备注
     * 
     * varchar 765  
     */
    @Column(name = column_remark)
    private String remark;
    
    /**
     * 状态(1启用0停用)
     * 
     * int 0  
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
     * 获取 应用名称
     */
    public String getAppName(){
        return this.appName;
    }
    
    /**
     * 设置 应用名称
     */
    public void setAppName(String appName){
        this.appName = appName;
    }
    

    /**
     * 获取 应用KEY
     */
    public String getAppKey(){
        return this.appKey;
    }
    
    /**
     * 设置 应用KEY
     */
    public void setAppKey(String appKey){
        this.appKey = appKey;
    }
    

    /**
     * 获取 应用密钥
     */
    public String getAppSecret(){
        return this.appSecret;
    }
    
    /**
     * 设置 应用密钥
     */
    public void setAppSecret(String appSecret){
        this.appSecret = appSecret;
    }
    

    /**
     * 获取 认证凭据
     */
    public String getAccessToken(){
        return this.accessToken;
    }
    
    /**
     * 设置 认证凭据
     */
    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
    

    /**
     * 获取 认证有效期
     */
    public java.util.Date getTokenExpire(){
        return this.tokenExpire;
    }
    
    /**
     * 设置 认证有效期
     */
    public void setTokenExpire(java.util.Date tokenExpire){
        this.tokenExpire = tokenExpire;
    }
    

    /**
     * 获取 备注
     */
    public String getRemark(){
        return this.remark;
    }
    
    /**
     * 设置 备注
     */
    public void setRemark(String remark){
        this.remark = remark;
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
