package org.mardon.spring;

import org.junit.jupiter.api.Test;
import org.mardon.spring.database.pool.ConnectionPool;
import org.mardon.spring.database.repository.CompanyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class XmlConfigurationTest {
    @Test
    void beanConfigurationTest(){
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            var pool = context.getBean("p1", ConnectionPool.class);
            var companyRepository = context.getBean("companyRepository", CompanyRepository.class);
            System.out.println(pool);
            System.out.println(companyRepository);
        }

        assertSoftly(s -> {
//            s.assertThat(pool.)
        });

    }
}
