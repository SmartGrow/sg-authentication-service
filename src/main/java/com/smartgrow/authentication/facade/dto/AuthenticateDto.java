package com.smartgrow.authentication.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class AuthenticateDto {
    @NotEmpty
    private String authorizationToken;
    private String authenticationToken;
}
