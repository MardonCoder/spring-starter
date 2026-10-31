package org.mardon.spring.integration.database.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.repository.CompanyRepository;
import org.mardon.spring.integration.annotation.IT;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
//@Rollback // по дефолту стоит в IT
class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 4;

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries(){
        companyRepository.findByName("Google");
        companyRepository.findAllByNameContainingIgnoreCase("a");
    }

    @Test
    void delete(){
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void findById(){
//        transactionTemplate.executeWithoutResult(tx->{
            var company = entityManager.find(Company.class, 1);
            assertThat(company).isNotNull();
            assertThat(company.getLocales()).hasSize(2);
            //        });
    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertThat(company.getId()).isNotNull();
    }
}