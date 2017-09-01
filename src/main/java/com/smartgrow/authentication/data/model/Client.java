package com.smartgrow.authentication.data.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "client")
public class Client extends AuditableEntity {

    @Id
    private String id;
    private String clientName;
    private Boolean active = false;
    private Long tokenDuration = 0L;
    private Boolean tokenRenewable = false;
    private Boolean alwaysRenew = false;
}
