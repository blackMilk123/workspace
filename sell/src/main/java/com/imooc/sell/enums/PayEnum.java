package com.imooc.sell.enums;

public enum PayEnum {

    NOT_PAY(0,"未支付"),
    PAY_SUCCESS(1,"支付成功");


    private Integer code;

    private String meassage;

    PayEnum(Integer code, String message) {
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
