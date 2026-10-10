package org.mardon.spring.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("pool1")
public class ConnectionPool {
    private final String username;
    private final Integer poolSize;
//    private List<Object> args;
//    private Map<String, Object> properties;

//    @Autowired
    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.pool.size}") Integer poolSize
//            , List<Object> args, Map<String, Object> properties
    ) {
        this.username = username;
        this.poolSize = poolSize;
//        this.args = args;
//        this.properties = properties;
    }

    // only for singletones
    @PostConstruct
    private void init(){
        System.out.println("Init connection pool");
    }

    @PreDestroy
    private void destroy(){
        System.out.println("Closing connection pool");
    }
}
