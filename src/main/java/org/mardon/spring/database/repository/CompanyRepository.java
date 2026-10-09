package org.mardon.spring.database.repository;

import jakarta.annotation.PostConstruct;
import org.mardon.spring.bpp.Auditing;
import org.mardon.spring.bpp.Transaction;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

@Auditing
@Transaction
public class CompanyRepository implements  CrudRepository<Integer, Company> {

//    @InjectBean
//    @Resource(name = "pool1")

//    @Autowired
//    @Qualifier("pool1")
    private ConnectionPool pool1;
    @Autowired
    private List<ConnectionPool> pools;
    @Value("${db.pool.size}")
    private Integer poolSize;

    @PostConstruct
    private void init(){
        System.out.println("Initializing company rep");
    }

    public ConnectionPool getPool1(){
        return pool1;
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

    @Autowired
    @Qualifier("pool1")
    public void setPool1(ConnectionPool pool1) {
        this.pool1 = pool1;
    }
}
