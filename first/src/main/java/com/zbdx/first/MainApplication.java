package com.bimowo.first;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(value="com.bimowo.first.dao")
public class MainApplication {
    public static void main(String[] args){
        SpringApplication.run(MainApplication.class, args);
    }
}
