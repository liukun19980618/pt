package com.pt.ptdealerproc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.pt"})
public class PtDealerProcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtDealerProcApplication.class, args);
    }

}
