package com.pt.ptportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.pt"})
public class PtPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtPortalApplication.class, args);
    }

}
