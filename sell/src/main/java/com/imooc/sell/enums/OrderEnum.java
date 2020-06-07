package com.imooc.sell.enums;

public enum OrderEnum {

    NEW_ORDER(0,"新订单"),
    CANCEL_ORDER(1,"已取消"),
    ORDER_FINISHED(2,"完结");


    private Integer code;
    private String meassage;

    OrderEnum(Integer code, String message) {
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
