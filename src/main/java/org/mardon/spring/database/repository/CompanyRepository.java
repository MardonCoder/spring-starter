package org.mardon.spring.database.repository;

import org.mardon.spring.bpp.InjectBean;
import org.mardon.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;

    public ConnectionPool getConnectionPool(){
        return connectionPool;
    }
}
