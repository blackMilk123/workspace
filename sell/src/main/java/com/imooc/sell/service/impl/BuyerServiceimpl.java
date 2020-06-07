package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BuyerServiceimpl implements BuyerService {
    @Autowired
    OrderMasterService orderMasterService;
    @Override
    public OrderDTO findOneOrder(String opneid, String orderid) {
        return this.checkOrderOwner(opneid, orderid);
    }

    @Override
    public OrderDTO cancel(String openid, String orderid) {
        OrderDTO orderDTO = this.checkOrderOwner(openid, orderid);
        if (orderDTO == null){
             throw new SellException(ResultEnum.ORDER_NOT_EXIST);
         }

        return orderMasterService.cancelOrder(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderMasterService.findOneOrder(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {

            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
