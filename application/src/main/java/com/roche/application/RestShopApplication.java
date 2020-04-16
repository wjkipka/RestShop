package com.roche.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 15.04.20.
 *
 * @author waldemarkipka
 */
@SpringBootApplication
@ComponentScan("com.roche.application")
@EnableAutoConfiguration
public class RestShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestShopApplication.class, args);
    }

}
