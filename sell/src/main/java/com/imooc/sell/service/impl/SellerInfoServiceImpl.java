package com.imooc.sell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.sell.entity.SellerInfo;
import com.imooc.sell.mapper.SellerInfoMapper;
import com.imooc.sell.service.ISellerInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 卖家信息表 服务实现类
 * </p>
 *
 * @author black_milk
 * @since 2019-12-08
 */
@Service
public class SellerInfoServiceImpl extends ServiceImpl<SellerInfoMapper, SellerInfo> implements ISellerInfoService {

}
