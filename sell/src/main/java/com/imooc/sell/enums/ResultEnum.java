package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {


    SUCCESS(0,"成功"),
    PRODUCT_STOCK_ERROR(1,"商品库存不足"),
    PRODUCT_NOT_EXIST(2,"商品不存在"),
    ORDER_NOT_EXIST(3,"订单不存在"),
    ORDER_STATUS_ERROR(4,"订单状态异常"),
    ORDER_UPDATE_FAIL(5,"订单更新异常"),
    PAY_STATUS_ERROR(6,"支付状态异常"),
    PARAM_ERROR(7,"参数不正确"),
    CART_NOT_EMPTY(8,"购物车不能为空"),
    ORDER_OWNER_ERROR(9,"订单不属于该用户");


    private Integer code;
    private String meassage;

    ResultEnum(Integer code, String message) {
        this.code =code;
        this.meassage = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMeassage() {
        return meassage;
    }





}
