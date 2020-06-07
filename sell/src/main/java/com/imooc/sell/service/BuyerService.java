package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

public interface BuyerService {
    //订单详情
    OrderDTO findOneOrder(String opneid,String orderid);
    //取消订单
    OrderDTO cancel(String openid,String orderid);
}
