package com.imooc.sell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.dto.OrderDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author black_milk
 * @since 2019-12-08
 */
public interface OrderMasterService extends IService<OrderMaster> {


    //创建订单
    OrderDTO createOrder(OrderDTO orderDTO) throws Exception;

    //查询单个订单
    OrderDTO findOneOrder(String OrderId);

    //查询订单列表
    List<OrderDTO> findOrderList(String openId);

    //取消订单
    OrderDTO cancelOrder(OrderDTO orderDTO);

    //完结订单
    OrderDTO finishOrder(OrderDTO orderDTO);

    //支付订单
    OrderDTO payOrder(OrderDTO orderDTO);

}
