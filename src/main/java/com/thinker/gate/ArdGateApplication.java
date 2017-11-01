package com.thinker.gate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.thinker.gate.dao.MysqlMapper;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackageClasses = { MysqlMapper.class })
@ImportResource({ "classpath:config/shiro/shiro-config.xml" })
public class ArdGateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArdGateApplication.class, args);
	}
}
