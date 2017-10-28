package com.thinker.gate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.thinker.gate.dao.MysqlDao;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackageClasses={MysqlDao.class})
public class ArdGateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArdGateApplication.class, args);
	}
}
