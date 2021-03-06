package com.smartgrow.authentication.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class ClientFacadeDto {

    @NotEmpty
    private String clientName;
    private Boolean active;
    private Long tokenDuration;
    private Boolean tokenRenewable;
    private Boolean alwaysRenew;
}
