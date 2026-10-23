package org.mardon.spring.integration;

import org.mardon.spring.database.pool.ConnectionPool;
import org.mardon.spring.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@TestConfiguration
public class TestApplicationRunner {

    // не работает в новых версиях
//    @MockitoSpyBean(name = "pool1")
//    private ConnectionPool pool1;
}
