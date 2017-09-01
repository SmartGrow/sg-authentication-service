package com.smartgrow.authentication.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationToken {
    private String token;
    private Long duration;
    private Boolean renewable;
}
