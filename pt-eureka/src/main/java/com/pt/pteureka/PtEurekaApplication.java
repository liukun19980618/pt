package com.pt.pteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PtEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtEurekaApplication.class, args);
    }

}
