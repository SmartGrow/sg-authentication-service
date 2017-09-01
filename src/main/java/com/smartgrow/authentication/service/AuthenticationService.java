package com.smartgrow.authentication.service;

import com.google.common.base.Preconditions;
import com.smartgrow.authentication.data.model.Client;
import com.smartgrow.authentication.data.model.Token;
import com.smartgrow.authentication.service.dto.AuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TokenService tokenService;

    public AuthenticationToken authenticate(String authorizationToken, String authenticationToken) {
        Preconditions.checkArgument(authorizationToken != null, "authorizationToken is mandatory");

        Client client = clientService.safelyGetClient(authorizationToken);

        Token token;
        if (StringUtils.isBlank(authenticationToken)) {
            tokenService.deleteAllTokensForClient(client.getId());
            token = tokenService.create(client.getId(), client.getTokenDuration(), client.getTokenRenewable(), client.getAlwaysRenew());
        } else {
            token = tokenService.safelyGetToken(authenticationToken);
        }

        Preconditions.checkState(!tokenService.isExpired(token), "authenticationToken has expired");

        return this.createAuthenticationToken(token);
    }

    public AuthenticationToken renewAuthToken(String apiAuthToken) {
        Preconditions.checkArgument(apiAuthToken != null, "authorizationToken is mandatory");

        Token token = tokenService.renew(apiAuthToken);

        return this.createAuthenticationToken(token);
    }

    private AuthenticationToken createAuthenticationToken(Token token) {
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(token.getId());
        authenticationToken.setDuration(token.getDuration());
        authenticationToken.setRenewable(token.getRenewable());
        return authenticationToken;
    }
}
