package org.mardon.spring.database.repository;

import org.mardon.spring.database.entity.User;
import org.mardon.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}
