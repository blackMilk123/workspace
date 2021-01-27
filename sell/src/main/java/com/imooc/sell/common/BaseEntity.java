package com.imooc.sell.common;

/**
 * @author ：David.Yan
 * @date ：Created in 2019/3/10 19:51
 * @description：
 * @modified By：
 * @version:
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public <T> QueryWrapper<T> toWrapper(T ob ) throws IllegalAccessException, InstantiationException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        queryWrapper.setEntity((T) ob.getClass().newInstance());
        Class uCla = ob.getClass();
        //得到类里面所有属性的集合
        Field[] fields =uCla.getDeclaredFields();

        for(Field item : fields){
            if(!item.getName().equalsIgnoreCase("serialVersionUID")){
                item.setAccessible(true); // 设置些属性是可以访问的
                Object value=item.get(ob);
                String tableName = "";
                for(Annotation an:item.getAnnotations()){
                    if(an instanceof TableField){
                        tableName = ((TableField) an).value();
                    }else if(an instanceof TableId){
                        tableName = ((TableId) an).value();
                    }
                }
                if(value instanceof String){
                    if(!StringUtils.isEmpty(value.toString().trim())){
                        queryWrapper.like(tableName,value.toString().trim());
                    }
                }else if(value instanceof Long || value instanceof BigDecimal || value instanceof Integer ){
                    if(value!=null || !"0".equalsIgnoreCase(value.toString())){
                        queryWrapper.eq(tableName, value);
                    }
                }else if(value instanceof LocalDateTime){
                    if(value!=null || !"0".equalsIgnoreCase(value.toString())){
                        //queryWrapper.eq(tableName, value);
                        LocalDateTime time = (LocalDateTime) value;
                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String  dt = df.format(time);
                        queryWrapper.eq("datediff("+tableName+",'"+dt+"')","0");
                    }
                }
            }
        }

        return queryWrapper;
    }
}

