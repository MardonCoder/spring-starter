package org.mardon.spring.database.repository;

import jakarta.annotation.PostConstruct;
import org.mardon.spring.bpp.Auditing;
import org.mardon.spring.bpp.InjectBean;
import org.mardon.spring.bpp.Transaction;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.pool.ConnectionPool;

import java.util.Optional;

@Auditing
@Transaction
public class CompanyRepository implements  CrudRepository<Integer, Company> {

    @InjectBean
    private ConnectionPool connectionPool;

    @PostConstruct
    private void init(){
        System.out.println("Initializing company rep");
    }

    public ConnectionPool getConnectionPool(){
        return connectionPool;
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.printf("Finding by id %d...%n", id);
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.printf("Deleting by id %d...%n", entity.id());
    }
}
