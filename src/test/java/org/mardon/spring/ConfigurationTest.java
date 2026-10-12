package org.mardon.spring;

import org.junit.jupiter.api.Test;
import org.mardon.spring.config.ApplicationConfiguration;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.pool.ConnectionPool;
import org.mardon.spring.database.repository.CrudRepository;
import org.mardon.spring.dto.CompanyReadDto;
import org.mardon.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class ConfigurationTest {
    @Test
    void xmlConfigurationTest(){
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            var pool = context.getBean("pool1", ConnectionPool.class);
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(pool);
            System.out.println(companyRepository);
            Company foundCompany = (Company) companyRepository.findById(10).orElse(null);
            assertThat(foundCompany.id()).isEqualTo(10);
        }
    }

    @Test
    void javaAndAnnotationsConfTest(){
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("prod", "smth");
            context.refresh();

            var companyService = context.getBean("companyService", CompanyService.class);
            CompanyReadDto foundCompany = companyService.findById(10).orElse(null);
            assertThat(foundCompany.id()).isEqualTo(10);
        }
    }
}
