package org.mardon.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.repository.CompanyRepository;
import org.mardon.spring.database.repository.CrudRepository;
import org.mardon.spring.dto.CompanyReadDto;
import org.mardon.spring.listener.entity.EntityEvent;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    public static final Integer COMPANY_ID = 1;

    @Mock
    private CrudRepository<Integer, Company> companyRepository;
    @Mock
    private UserService userService;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        doReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap()))).when(companyRepository).findById(COMPANY_ID);

        var actualResult = companyService.findById(COMPANY_ID);
        var expectedResult = new CompanyReadDto(COMPANY_ID);

        assertThat(actualResult).isPresent().get().isEqualTo(expectedResult);

        verify(eventPublisher).publishEvent(any(EntityEvent.class));
        verifyNoMoreInteractions(eventPublisher, userService);
    }
}