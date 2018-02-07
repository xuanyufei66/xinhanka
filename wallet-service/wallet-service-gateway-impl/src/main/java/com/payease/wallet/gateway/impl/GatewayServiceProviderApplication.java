package com.payease.wallet.gateway.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lch on 2017/11/8.
 */
@SpringBootApplication
//@ComponentScan("com.payease.wallet")
//@MapperScan("com.payease.wallet.orm")
@ImportResource("classpath:dubbo-provider.xml")
public class GatewayServiceProviderApplication {
    private static final Logger log = LoggerFactory.getLogger(GatewayServiceProviderApplication.class);


    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }



    public static void main(String[] args) throws InterruptedException {


        ApplicationContext ctx =
                new SpringApplicationBuilder().sources(GatewayServiceProviderApplication.class).web
                        (false).run(args);
        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();


    }
}
