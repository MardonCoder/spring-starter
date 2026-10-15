package org.mardon.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner {

    static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println("All good");
    }
}
