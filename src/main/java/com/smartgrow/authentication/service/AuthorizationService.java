package com.smartgrow.authentication.service;

import com.google.common.base.Preconditions;
import com.smartgrow.authentication.data.model.Client;
import com.smartgrow.authentication.service.dto.AuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private ClientService clientService;

    public AuthorizationToken authorize(String clientName) {
        Preconditions.checkArgument(clientName != null, "clientName is mandatory");

        Client client = clientService.safelyGetClientByClientName(clientName);
        Preconditions.checkState(client.getActive(), "Client is not currently active");

        AuthorizationToken authorizationToken = new AuthorizationToken();
        authorizationToken.setToken(client.getId());

        return authorizationToken;
    }
}
