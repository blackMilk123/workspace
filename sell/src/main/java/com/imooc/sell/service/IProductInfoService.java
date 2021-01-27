package com.imooc.sell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.ProductInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author black_milk
 * @since 2019-12-08
 */
public interface IProductInfoService extends IService<ProductInfo> {

    void decreaseStock(List<OrderDetail> list);

    void increaseStock(List<OrderDetail> orderDetailList);
}
