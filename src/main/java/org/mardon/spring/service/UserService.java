package org.mardon.spring.service;

import lombok.RequiredArgsConstructor;
import org.mardon.spring.database.entity.Company;
import org.mardon.spring.database.repository.CompanyRepository;
import org.mardon.spring.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

//    public UserService(@Qualifier("userRepository") UserRepository userRepository, CrudRepository<Integer, Company> companyRepository) {
//        this.userRepository = userRepository;
//        this.companyRepository = companyRepository;
//    }
}
