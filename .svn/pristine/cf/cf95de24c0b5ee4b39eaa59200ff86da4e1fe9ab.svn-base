package com.payease.wallet.scheduling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by zhangzhili on 2017/11/20.
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan("com.payease.wallet")
@MapperScan("com.payease.wallet.orm")
@ImportResource("classpath:dubbo-consumer.xml")
public class SchedulingApplication {


    public static void main(String[] args) {
        SpringApplication.run(SchedulingApplication.class, args);
    }
}
