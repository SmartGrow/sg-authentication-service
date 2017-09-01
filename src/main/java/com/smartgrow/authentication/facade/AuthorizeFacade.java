package com.smartgrow.authentication.facade;

import com.smartgrow.authentication.facade.dto.AuthorizeDto;
import com.smartgrow.authentication.service.AuthorizationService;
import com.smartgrow.authentication.service.dto.AuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthorizeFacade {

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public AuthorizationToken authorize(@Valid @RequestBody AuthorizeDto payload) {
        return authorizationService.authorize(payload.getClientName());
    }
}
