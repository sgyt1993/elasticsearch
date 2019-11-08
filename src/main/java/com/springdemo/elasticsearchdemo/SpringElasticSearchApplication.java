package com.springdemo.elasticsearchdemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 适用于  es  7.x的版本
 */

@SpringBootApplication
public class SpringElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringElasticSearchApplication.class, args);
    }

}



