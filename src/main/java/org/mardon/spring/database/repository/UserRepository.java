package org.mardon.spring.database.repository;

import org.mardon.spring.database.pool.ConnectionPool;

public class UserRepository {

    private final ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
