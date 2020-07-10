package com.pt.ptuser;



import com.pt.ptcommonsecurity.annotation.EnableCustomResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCustomResourceServer
public class PtUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtUserApplication.class, args);
    }

}
