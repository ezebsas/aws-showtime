package com.tacs.grupo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class Grupo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Grupo2Application.class, args);
    }

}
