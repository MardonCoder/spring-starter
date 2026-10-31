package org.mardon.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mardon.spring.database.entity.User;
import org.mardon.spring.database.repository.UserRepository;
import org.mardon.spring.integration.annotation.IT;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkQueries(){
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }
}