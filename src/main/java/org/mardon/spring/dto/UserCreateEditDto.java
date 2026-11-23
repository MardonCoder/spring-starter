package org.mardon.spring.dto;

import lombok.Value;
import org.mardon.spring.database.entity.Role;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    String username;
    LocalDate birthDate;
    String firstName;
    String lastName;
    Role role;
    Integer companyId;


}
