package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

//自定义异常继承runtimeException
public class SellException extends RuntimeException {

    private Integer code;
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMeassage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
