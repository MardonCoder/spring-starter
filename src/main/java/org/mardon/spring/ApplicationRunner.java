package org.mardon.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        log.info("All good");
    }

    // метод создает, настраивает и инициализирует объект, управление жизненным циклом которого передается Spring
    // IoC-контейнеру. Возвращаемый методом объект регистрируется в контексте как Spring Bean и становится
    // доступен для внедрения зависимостей (@Autowired, внедрение через конструктор и т.д.).
    @Bean
    // этот интерфейс выполняет код сразу после запуска приложения, но до его начала работы
    CommandLineRunner checkDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            String dbVersion = jdbcTemplate.queryForObject("SELECT version();", String.class);
            System.out.println("==================================================");
            System.out.println(">>> УСПЕШНОЕ ПОДКЛЮЧЕНИЕ К POSTGRESQL ЧЕРЕЗ DOCKER COMPOSE!");
            System.out.println(">>> Версия базы данных: " + dbVersion);
            System.out.println("==================================================");
        };
    }
}
