package org.mardon.spring.integration.database.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.integration.annotation.IT;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
@Transactional
@Commit
//@Rollback // по дефолту стоит в IT
class CompanyRepositoryTest {

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

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