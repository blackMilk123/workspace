package com.imooc.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.mapper.ProductInfoMapper;
import com.imooc.sell.service.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author black_milk
 * @since 2019-12-08
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Override
    @Transactional
    public void decreaseStock(List<OrderDetail> list) {


        for (OrderDetail orderDetail : list) {

            QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<ProductInfo>().eq("product_id", orderDetail.getProductId());
            ProductInfo productInfo = productInfoMapper.selectOne(queryWrapper);
            if (productInfo ==null){
                new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - orderDetail.getProductQuantity();
            if (result < 0){
                new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            productInfoMapper.update(productInfo,queryWrapper);
           // productInfoMapper.updateById(productInfo);




        }
    }

    @Override
    public void increaseStock(List<OrderDetail> orderDetailList) {

        for (OrderDetail orderDetail : orderDetailList) {
            QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<ProductInfo>().eq("product_id", orderDetail.getProductId());
            ProductInfo productInfo = productInfoMapper.selectOne(queryWrapper);
            if (productInfo ==null){
                new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() + orderDetail.getProductQuantity();
            if (result < 0){
                new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            productInfoMapper.update(productInfo,queryWrapper);
        }
    }
}
