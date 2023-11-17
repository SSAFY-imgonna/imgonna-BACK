package com.ssafy.imgonna.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.ssafy.imgonna.**.mapper" })
public class DBConfiguration {

}
