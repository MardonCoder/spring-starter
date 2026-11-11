package org.mardon.spring.integration;

import org.junit.jupiter.api.BeforeAll;
import org.mardon.spring.integration.annotation.IT;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

/*
можно заместо этого использовать Liquibase Contexts (context: test):
    В миграции Liquibase прописывается context: test.
    В application-test.yml задается spring.liquibase.contexts: test.
    Данные накатятся ровно один раз при инициализации схемы Testcontainers.
 */
@Sql({  // Можно ставить над методом если нужно перед определенным методом кидать данные
        "classpath:sql/data.sql"
})
@IT
@Transactional
public abstract class IntegrationTestBase {
    // излишно в новых версиях
//    private static final PostgreSQLContainer container = new PostgreSQLContainer("postgres:18.6-alpine");
//
//    @BeforeAll
//    static void runContainer(){
//        container.start();
//    }
//
//    @DynamicPropertySource
//    static void postgresProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//    }

    @ServiceConnection // делает всё автоматически
    private static final PostgreSQLContainer container =
            new PostgreSQLContainer("postgres:18.6-alpine");

    static {
        container.start();
    }
}
