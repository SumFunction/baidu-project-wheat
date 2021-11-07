package com.lrr.demo.generator.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MyMetaObjectHandler implements MetaObjectHandler {

    //分页查询配置
        @Bean
      public PaginationInterceptor paginationInterceptor() {
                PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
                return paginationInterceptor;
            }
    //添加时调用
    @Override
    public void insertFill(MetaObject metaObject) {
        //arg1:自动填充的字段名称arg2:自动填充的值arg3: meta0bject固定写法
        Date date = new Date();
        long times = date.getTime();

        try{
            this.setFieldValByName("publishTime", times,metaObject);

        }catch (Exception e){
            this.setFieldValByName("publishTime", date,metaObject);
        }

    }

    //更新是调用
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("publishTime", new Date(),metaObject);
    }
}

