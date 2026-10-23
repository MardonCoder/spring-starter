package org.mardon.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mardon.spring.ApplicationRunner;
import org.mardon.spring.config.DatabaseProperties;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.dto.CompanyReadDto;
import org.mardon.spring.integration.annotation.IT;
import org.mardon.spring.listener.entity.EntityEvent;
import org.mardon.spring.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@IT
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
//@ActiveProfiles("test")
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationRunner.class, initializers = ConfigDataApplicationContextInitializer.class)
public class CompanyServiceIT {
    public static final Integer COMPANY_ID = 1;

//    @Autowired
    private final CompanyService companyService;
//    @Autowired
    private final DatabaseProperties databaseProperties;

    @Test
    void findById(){
        var actualResult = companyService.findById(COMPANY_ID);
        var expectedResult = new CompanyReadDto(COMPANY_ID);

        assertThat(actualResult).isPresent().get().isEqualTo(expectedResult);
    }
}
