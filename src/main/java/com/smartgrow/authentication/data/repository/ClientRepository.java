package com.smartgrow.authentication.data.repository;

import com.smartgrow.authentication.data.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ClientRepository extends MongoRepository<Client, String> {

    List<Client> findByClientName(String clientName);
}
