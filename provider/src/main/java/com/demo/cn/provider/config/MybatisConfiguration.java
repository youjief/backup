package com.demo.cn.provider.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.demo.cn.common.mapper")
public class MybatisConfiguration {
}
