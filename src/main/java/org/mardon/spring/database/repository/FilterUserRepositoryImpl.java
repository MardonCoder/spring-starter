package org.mardon.spring.database.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.mardon.generated.jooq.tables.Users;
import org.mardon.spring.database.entity.User;
import org.mardon.spring.dto.UserFilter;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final DSLContext dslContext;
    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        Condition condition = DSL.noCondition();

        if (filter.firstname() != null) {
            condition = condition.and(Users.USERS.FIRSTNAME.contains(filter.firstname()));
        }
        if (filter.lastname() != null) {
            condition = condition.and(Users.USERS.LASTNAME.contains(filter.lastname()));
        }
        if (filter.birthdate() != null) {
            condition = condition.and(Users.USERS.BIRTH_DATE.lessThan(filter.birthdate()));
        }

        List<Long> ids = dslContext.select(Users.USERS.ID)
                .from(Users.USERS)
                .where(condition)
                .orderBy(Users.USERS.FIRSTNAME.asc())
                .fetch(Users.USERS.ID);

        if (ids.isEmpty()) {
            return Collections.emptyList();
        }

        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.id IN :ids ORDER BY u.firstName ASC", User.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}
