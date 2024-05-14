package com.bsg6.chapter10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.bsg6.chapter09.jpa",
    "com.bsg6.chapter10"
})
public class GatewayRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayRestApplication.class, args);
    }
}
