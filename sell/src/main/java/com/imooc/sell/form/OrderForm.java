package com.imooc.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;


    @NotEmpty(message = "联系方式必填")
    private String phone;


    @NotEmpty(message = "收货地址必填")
    private String address;

    @NotEmpty(message = "微信id必填")
    private  String opneid ;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
