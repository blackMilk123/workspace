package com.imooc.sell.controller;


import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.convert.OrderFormToOrderDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderMasterService;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author black_milk
 * @since 2019-12-08
 */
@RestController
@RequestMapping("/buyer/order")
public class OrderMasterController {

    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private BuyerService buyerService;


    @RequestMapping("/create")
    @ResponseBody
    public ResultVO<Map<String,String>> createOrder(@Valid OrderForm orderform, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            //参数异常则抛出 表单定义的信息
            throw  new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTO.convert(orderform);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw  new SellException(ResultEnum.CART_NOT_EMPTY);
        }
        OrderDTO order = orderMasterService.createOrder(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", order.getOrderId());
        return ResultVOUtil.success(map);

    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultVO<List<OrderMaster>> list(@RequestParam(value = "openid")String openid,
                                            @RequestParam(value = "size",defaultValue ="10") Integer size,
                                            @RequestParam(value = "page",defaultValue = "0") Integer page){

        List<OrderDTO> orderList = orderMasterService.findOrderList(openid);
        return ResultVOUtil.success(orderList);

    }

    @RequestMapping("/detail")
    @ResponseBody
    public ResultVO<OrderDTO> detail(@RequestParam(value = "openid")String openid,
                                     @RequestParam(value = "orderid") String orderid){
        OrderDTO orderDTO = buyerService.findOneOrder(openid, orderid);
        return ResultVOUtil.success(orderDTO);

    }
    @ResponseBody
    @RequestMapping("/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam(value = "openid")String openid,
                                     @RequestParam(value = "orderid") String orderid){

        OrderDTO orderDTO = buyerService.cancel(openid, orderid);
        return ResultVOUtil.success(orderDTO);
    }

}
