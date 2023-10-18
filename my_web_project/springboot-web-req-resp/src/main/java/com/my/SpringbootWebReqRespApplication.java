package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"com.my","dao"})
@SpringBootApplication //扫描当前目录下包和他们的子包
public class SpringbootWebReqRespApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebReqRespApplication.class, args);
	}

}
