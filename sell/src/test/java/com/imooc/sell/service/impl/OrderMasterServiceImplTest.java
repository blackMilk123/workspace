package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.ProductInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMasterServiceImplTest {
    @Autowired
    private OrderMasterServiceImpl service;
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    void createOrder() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("杨戬");
        orderDTO.setBuyerPhone("11111");
        orderDTO.setBuyerAddress("九江学院");
        orderDTO.setBuyerOpenid("111");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("4");
        orderDetail.setProductQuantity(1);
        OrderDetail orderDetail2 = new OrderDetail();

        orderDetail2.setProductId("5");
        orderDetail2.setProductQuantity(2);
        ArrayList<OrderDetail> details = new ArrayList<>();
        details.add(orderDetail);
        details.add(orderDetail2);
        orderDTO.setOrderDetailList(details);

        OrderDTO order = service.createOrder(orderDTO);



    }
    @Test
    void  testUpdate(){

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("5");
        productInfo.setProductStock(101);
        productInfoService.saveOrUpdate(productInfo);
    }
}