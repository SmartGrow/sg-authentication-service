package com.smartgrow.authentication.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClientServiceDto {
    private String id;
    private String clientName;
    private Boolean active;
    private Long tokenDuration;
    private Boolean tokenRenewable;
    private Boolean alwaysRenew;
    private Date createdDate;
}
