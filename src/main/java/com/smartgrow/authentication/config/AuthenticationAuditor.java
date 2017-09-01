package com.smartgrow.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuthenticationAuditor implements AuditorAware<String> {

    @Value("${spring.application.name}")
    private String auditor;

    @Override
    public String getCurrentAuditor() {
        return this.auditor;
    }
}
