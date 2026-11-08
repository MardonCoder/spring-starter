package org.mardon.spring.database.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.mardon.generated.jooq.tables.Users;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.entity.Role;
import org.mardon.spring.database.entity.User;
import org.mardon.spring.dto.PersonalInfo;
import org.mardon.spring.dto.UserFilter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_USER_BY_COMPANY_AND_ROLE = """
            SELECT firstname, 
                   lastname, 
                   birth_date 
            FROM users
            WHERE company_id = ? AND role = ?
            """;

    //language=PostgreSQL
    private static final String UPDATE_COMPANY_AND_ROLE = """
            UPDATE users
            SET company_id = ?,
                role = ?
            WHERE id = ?
            """;

    private static final String UPDATE_COMPANY_AND_ROLE_NAMED = """
            UPDATE users
            SET company_id = :companyId,
                role = :role
            WHERE id = :id
            """;

    private final DSLContext dslContext;
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_USER_BY_COMPANY_AND_ROLE,
                (rs, rowNum) -> new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getObject("birth_date", LocalDate.class)
                ), companyId, role.name());
    }

    @Override
    public void updateCompanyAndRole(List<User> users) {
        var args = users.stream()
                .map(user -> new Object[]{user.getCompany().getId(), user.getRole().name(), user.getId()})
                .toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, args);
    }

    @Override
    public void updateCompanyAndRoleNamed(List<User> users) {
        var args = users.stream()
                .map(user -> Map.of(
                        "companyId", user.getCompany().getId(),
                        "role", user.getRole().name(),
                        "id", user.getId()
                )).map(MapSqlParameterSource::new).toArray(MapSqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE_NAMED, args);
    }
}







