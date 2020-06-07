package com.imooc.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.sell.convert.ConvertOrderDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.OrderEnum;
import com.imooc.sell.enums.PayEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.mapper.OrderDetailMapper;
import com.imooc.sell.mapper.OrderMasterMapper;
import com.imooc.sell.mapper.ProductInfoMapper;
import com.imooc.sell.service.IProductInfoService;
import com.imooc.sell.service.OrderMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author black_milk
 * @since 2019-12-08
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService {


    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private IProductInfoService productInfoService;


    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) throws Exception {

        String orderId = UUID.randomUUID().toString();
        BigDecimal orderTotalPrice = new BigDecimal(BigInteger.ZERO);
        //获取订单中的商品
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            //拿到商品id
            String productId = orderDetail.getProductId();
            QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<ProductInfo>().eq("product_id", productId);
            ProductInfo productInfo = productInfoMapper.selectOne(queryWrapper);

            //ProductInfo productInfo = productInfoMapper.selectById(productId);

            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            if (productInfo.getProductStock()<= 0){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            //拿到总量 计算总价

            Integer productQuantity = orderDetail.getProductQuantity();
            BigDecimal productPrice = productInfo.getProductPrice();

           orderTotalPrice= productPrice.multiply(new BigDecimal(productQuantity)).add(orderTotalPrice);

           //保存订单详情
            orderDetail.setDetailId(UUID.randomUUID().toString());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailMapper.insert(orderDetail);

        }


        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderTotalPrice);
        orderMaster.setOrderStatus(OrderEnum.NEW_ORDER.getCode());
        orderMaster.setPayStatus(PayEnum.NOT_PAY.getCode());
        orderMasterMapper.insert(orderMaster);

        //减库存
        productInfoService.decreaseStock(orderDetailList);
        //带着订单id返回
        orderDTO.setOrderId(orderId);
        return orderDTO;
    }

    @Override
    public OrderDTO findOneOrder(String OrderId) {

        OrderMaster orderMaster = orderMasterMapper.selectById(OrderId);
        if (orderMaster == null){
          throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailMapper
                .selectList(new QueryWrapper<OrderDetail>().eq("order_id", OrderId));
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findOrderList(String openId) {


        QueryWrapper<OrderMaster> wrapper = new QueryWrapper<>();
        wrapper.eq("buyer_openid", openId);
        List<OrderMaster> orderMasterList = orderMasterMapper
                .selectList(wrapper);


        return ConvertOrderDTO.convertList(orderMasterList);

    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {


        //判断对应的订单状态
        //如果不是新订单
        if (!orderDTO.getOrderStatus().equals(OrderEnum.NEW_ORDER.getCode())){
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR) ;
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderEnum.CANCEL_ORDER.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int i = orderMasterMapper.updateById(orderMaster);

        if (i <= 0){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        productInfoService.increaseStock(orderDetailList);

        //如果已经支付 需要退款
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {

        //如果不是新订单
        if (!orderDTO.getOrderStatus().equals(OrderEnum.NEW_ORDER.getCode())){
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR) ;
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderEnum.ORDER_FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int i = orderMasterMapper.updateById(orderMaster);
        if (i <= 0){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO payOrder(OrderDTO orderDTO) {
        //如果不是新订单
        if (!orderDTO.getOrderStatus().equals(OrderEnum.NEW_ORDER.getCode())){
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR) ;
        }
        //判断支付状态
        OrderMaster orderMasterData = this.orderMasterMapper.selectById(orderDTO.getOrderId());
        if (!orderMasterData.getPayStatus().equals(PayEnum.NOT_PAY.getCode())){
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }
        //修改订单支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayEnum.PAY_SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int i = orderMasterMapper.updateById(orderMaster);
        if (i <= 0){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;

    }

}
