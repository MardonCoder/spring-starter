package org.mardon.spring.integration.annotation;

import org.mardon.spring.database.pool.ConnectionPool;
import org.mardon.spring.integration.TestApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@MockitoSpyBean(name = "pool1", types = ConnectionPool.class)
@SpringBootTest(classes = TestApplicationRunner.class)
public @interface IT {
}
