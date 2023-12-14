package com.binar.binarChallenge4.DTO;

import com.binar.binarChallenge4.Entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Builder
public class DTOFindUser {

    private String username;

    private String email;

    private String role;
}