package org.mardon.spring;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

//@EntityScan(basePackages = "com.mardon и так далее") // путь для ручной записи сущностей
@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        log.info("All good");

        var entityManager = context.getBean(EntityManager.class);

        var result = entityManager.createQuery("select count(u.id) from User u", Long.class).getSingleResult();
        System.out.println("~~~~~~~~~~~~~~~~~РАЗМЕР ПОЛУЧИВШИЙСЯ~~~~~~~~~~~~~~~~~~~~");
        System.out.println(result);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
