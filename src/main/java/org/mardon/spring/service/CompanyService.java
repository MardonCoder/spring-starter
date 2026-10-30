package org.mardon.spring.service;

import lombok.RequiredArgsConstructor;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.repository.CrudRepository;
import org.mardon.spring.dto.CompanyReadDto;
import org.mardon.spring.listener.entity.AccessType;
import org.mardon.spring.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id).map(entity -> {
            eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
            return new CompanyReadDto(entity.getId());
        });
    }
}

