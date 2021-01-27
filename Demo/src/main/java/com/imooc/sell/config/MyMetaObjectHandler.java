package com.imooc.sell.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler  {
  /*  @Override
    public void insertFill(MetaObject metaObject) {
        log.info("创建时间开始....");
        this.strictInsertFill(metaObject,"createTime", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());

    }*/
    @Override

    public void insertFill(MetaObject metaObject) {

        log.info("开始自动注入..............");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("version", 1, metaObject);
        this.setFieldValByName("deleted", 0, metaObject);

    }


    @Override

    public void updateFill(MetaObject metaObject) {
        log.info("开始自动注入..............");
        this.setFieldValByName("updateTime", new Date(), metaObject);

    }
}
