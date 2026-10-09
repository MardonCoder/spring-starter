package org.mardon.spring;

import org.junit.jupiter.api.Test;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.pool.ConnectionPool;
import org.mardon.spring.database.repository.CompanyRepository;
import org.mardon.spring.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class XmlConfigurationTest {
    @Test
    void beanConfigurationTest(){
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            var pool = context.getBean("p1", ConnectionPool.class);
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(pool);
            System.out.println(companyRepository);
            Company foundCompany = (Company) companyRepository.findById(10).orElse(null);
            assertSoftly(s -> {
                s.assertThat(foundCompany.id()).isEqualTo(10);
            });
        }

    }
}
