package com.payease.wallet.app.impl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzhili on 2017/11/15.
**/

@Component
public class RedisCommandLineRunner implements CommandLineRunner {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public void run(String... strings) throws Exception {
        stringRedisTemplate.setDefaultSerializer(new StringRedisSerializer());
    }
}
