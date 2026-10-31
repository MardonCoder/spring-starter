package org.mardon.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.mardon.spring.database.entity.User;
import org.mardon.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where u.firstName like %:firstname% and u.lastName like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query( value = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);
}
