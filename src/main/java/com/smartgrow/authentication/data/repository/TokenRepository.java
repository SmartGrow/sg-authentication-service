package com.smartgrow.authentication.data.repository;

import com.smartgrow.authentication.data.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TokenRepository extends MongoRepository<Token, String> {

    List<Token> findByClientId(String clientId);
}
