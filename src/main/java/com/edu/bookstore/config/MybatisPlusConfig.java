package com.edu.bookstore.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @className: MybatisPageConfig
 * @description: MybatisPage配置类
 * @author: Zi
 **/
@EnableTransactionManagement//开启事务
@Configuration
@MapperScan("com.edu.bookstore.mapper")//加载mp接口
public class MybatisPlusConfig{

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//Dbtype.数据库 可改成自己对应的数据库，当前为MySQL。
        return interceptor;
    }
}

