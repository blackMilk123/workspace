package com.imooc.sell.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.service.IProductCategoryService;
import com.imooc.sell.service.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author black_milk
 * @since 2019-12-08
 */
@RestController
@RequestMapping("/buyer/product")
public class ProductCategoryController  {
    @Autowired
    IProductCategoryService categoryService;
    @Autowired
    IProductInfoService productInfoService;

    @RequestMapping("/list")
    public Object list(){
        List<ProductCategory> list = categoryService.list();


        ArrayList<ProductVO> productVOArrayList = new ArrayList<>();

        for (ProductCategory productCategory : list) {

            Integer categoryType = productCategory.getCategoryType();


            QueryWrapper<ProductInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("category_Type",categoryType).eq("product_status","0");
            List<ProductInfo> productInfoList = productInfoService.list(wrapper);

            ProductVO productVO = new ProductVO();

            ArrayList<ProductInfoVO> productInfoVOArrayList = new ArrayList<>();

            for (ProductInfo productInfo : productInfoList) {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                productInfoVO.setProductDescription(productInfo.getProductDescription());
                productInfoVO.setProductIcon(productInfo.getProductIcon());
                productInfoVO.setProductId(productInfo.getProductId());
                productInfoVO.setProductName(productInfo.getProductName());
                productInfoVO.setProductPrice(productInfo.getProductPrice());
                productInfoVOArrayList.add(productInfoVO);
            }
            productVO.setProductInfoVOList(productInfoVOArrayList);
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            productVOArrayList.add(productVO);

        }

        ResultVO result = new ResultVO<>();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(productVOArrayList);
        return result;
    }

}
