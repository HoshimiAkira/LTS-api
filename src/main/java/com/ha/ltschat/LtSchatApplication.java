package com.ha.ltschat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.ha.ltschat.mapper"})
public class LtSchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(LtSchatApplication.class, args);
    }

}
