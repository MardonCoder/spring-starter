package org.mardon.spring.integration;

import org.junit.jupiter.api.BeforeAll;
import org.mardon.spring.integration.annotation.IT;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.postgresql.PostgreSQLContainer;

@Sql({  // Можно ставить над методом если нужно перед определенным методом кидать данные
        "classpath:sql/data.sql"
})
@IT
public abstract class IntegrationTestBase {
    private static final PostgreSQLContainer container = new PostgreSQLContainer("postgres:18.6");

    @BeforeAll
    static void runContainer(){
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
