package org.mardon.spring.database.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.mardon.spring.database.entity.User;
import org.mardon.spring.dto.UserFilter;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();

        return Collections.emptyList();
    }
}
