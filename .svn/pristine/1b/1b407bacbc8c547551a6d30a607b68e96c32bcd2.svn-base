package com.payease.wallet.app.impl;

import com.payease.wallet.app.impl.propertites.ApiCommProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangzhili on 2017/11/7.
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.payease.wallet")
@MapperScan("com.payease.wallet.orm")
//@EnableCaching
@ImportResource(locations={"classpath:dubbo-registry.xml"})
@EnableConfigurationProperties({ApiCommProperties.class})
public class AppServiceProviderApplication {

    private static final Logger log = LoggerFactory.getLogger(AppServiceProviderApplication.class);


    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }



    public static void main(String[] args) throws InterruptedException {


        ApplicationContext ctx =
            new SpringApplicationBuilder().sources(AppServiceProviderApplication.class).web
                (false).run(args);
        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();
    }

}
