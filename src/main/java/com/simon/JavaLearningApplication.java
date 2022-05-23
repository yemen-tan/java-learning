package com.simon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.simon.mapper")
public class JavaLearningApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaLearningApplication.class,args);
    }
}
