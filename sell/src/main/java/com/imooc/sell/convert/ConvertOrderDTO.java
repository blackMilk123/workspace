package com.imooc.sell.convert;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertOrderDTO {
    public static OrderDTO  Convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }
    public  static List<OrderDTO> convertList(List<OrderMaster> orderMasterList){

        List<OrderDTO> orderDTOList = orderMasterList.stream().map(e -> Convert(e)).collect(Collectors.toList());
        return orderDTOList;
    }

}
