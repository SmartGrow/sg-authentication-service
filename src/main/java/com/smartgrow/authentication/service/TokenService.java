package com.smartgrow.authentication.service;

import com.google.common.base.Preconditions;
import com.smartgrow.authentication.data.model.Token;
import com.smartgrow.authentication.data.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    Token create(String clientId, Long tokenDuration, Boolean isRenewable, Boolean alwaysRenew) {
        Preconditions.checkArgument(clientId != null, "clientId is mandatory");
        Preconditions.checkArgument(tokenDuration != null, "tokenDuration is mandatory");
        Preconditions.checkArgument(isRenewable != null, "isRenewable is mandatory");
        Preconditions.checkArgument(alwaysRenew != null, "alwaysRenew is mandatory");
        Preconditions.checkState(tokenDuration >= 0, "tokenDuration must be greater than or equal to 0");

        Token token = new Token();
        token.setClientId(clientId);
        token.setDuration(tokenDuration);
        token.setRenewable(isRenewable);
        token.setAlwaysRenew(alwaysRenew);

        return tokenRepository.save(token);
    }

    Token renew(String token) {
        Token foundToken = safelyGetToken(token);
        Preconditions.checkState(!isExpired(foundToken), "Token has expired and cannot be renewed");
        Preconditions.checkState(!foundToken.getRenewable(), "Token cannot be renewed");

        return tokenRepository.save(foundToken);
    }

    Token safelyGetToken(String token) {
        Preconditions.checkArgument(token != null, "Token is mandatory");

        Token foundToken = tokenRepository.findOne(token);
        Preconditions.checkState(foundToken != null, "Token not found");

        return foundToken;
    }

    boolean isExpired(Token token) {
        return token.getDuration() != 0 && (new Date().getTime() > (token.getLastModifiedDate() != null ? token.getLastModifiedDate() : token.getCreatedDate()).getTime() + token.getDuration());
    }

    void deleteAllTokensForClient(String clientId) {
        tokenRepository.delete(tokenRepository.findByClientId(clientId));
    }
}
