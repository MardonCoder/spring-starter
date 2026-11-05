package org.mardon.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mardon.spring.database.entity.Role;
import org.mardon.spring.database.entity.User;
import org.mardon.spring.database.repository.UserRepository;
import org.mardon.spring.dto.PersonalInfo;
import org.mardon.spring.dto.UserFilter;
import org.mardon.spring.integration.annotation.IT;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    @Commit
    void checkAuditing(){
        var ivan = userRepository.findById(1L).get();
        Instant now = Instant.now();
        var birthDate = ivan.getBirthDate();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1L));
        userRepository.flush();
        assertThat(ivan.getBirthDate()).isAfter(birthDate);
        assertThat(ivan.getUpdatedAt()).isAfter(now);
    }

    @Test
    void checkCustomImplementation(){
        UserFilter filter = new UserFilter(null, "%ov%", LocalDate.now());
        var users = userRepository.findAllByFilter(filter);
        assertThat(users).isEmpty();
    }

    @Test
    void checkProjections(){
        var users = userRepository.findAllByCompanyId(1);
        users.forEach(u -> System.out.println(u.toString()));
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    void checkQueries(){
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }

    @Test
    void checkUpdate(){
        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);
    }

    @Test
    void checkFirstTop(){
        var sortById = Sort.by("id").descending().and(Sort.by("firstName"));
        var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortById);
        assertThat(allUsers).hasSize(3);
        var topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkPageable(){
        var pageable = PageRequest.of(1, 2, Sort.by("id"));
        var slice = userRepository.findAllBy(pageable);

        slice.forEach(user -> System.out.println(user.getCompany().getName()));
        while (slice.hasNext()){
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }
}