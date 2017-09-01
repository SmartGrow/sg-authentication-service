package com.smartgrow.authentication.facade;

import com.smartgrow.authentication.facade.dto.AuthenticateDto;
import com.smartgrow.authentication.service.AuthenticationService;
import com.smartgrow.authentication.service.dto.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticateFacade {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public AuthenticationToken authenticate(@Valid @RequestBody AuthenticateDto payload) {
        return authenticationService.authenticate(payload.getAuthorizationToken(), payload.getAuthenticationToken());
    }
}