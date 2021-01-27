package com.imooc.sell.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.imooc.sell.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 卖家信息表
 * </p>
 *
 * @author black_milk
 * @since 2019-12-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SellerInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @TableId
    private String username;

    private String password;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
