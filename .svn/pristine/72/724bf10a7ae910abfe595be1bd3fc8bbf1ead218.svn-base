package com.payease.wallet.app;

import com.payease.wallet.app.properties.LoginProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangzhili on 2017/10/20.
 */
@SpringBootApplication
@RestController
@ComponentScan("com.payease.wallet")
@ImportResource("classpath:dubbo-consumer.xml")
@EnableConfigurationProperties({LoginProperties.class})
public class MainApplication {



    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
