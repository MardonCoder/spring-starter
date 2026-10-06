package org.mardon.spring;

import org.mardon.spring.database.pool.ConnectionPool;
import org.mardon.spring.database.repository.UserRepository;
import org.mardon.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        var pool = context.getBean("p1", ConnectionPool.class);
        System.out.println(pool);
    }
}
