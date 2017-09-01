package com.smartgrow.authentication.service;

import com.google.common.base.Preconditions;
import com.smartgrow.authentication.data.model.Client;
import com.smartgrow.authentication.data.repository.ClientRepository;
import com.smartgrow.authentication.service.dto.ClientServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientServiceDto save(ClientServiceDto clientServiceDto) {

        List<Client> byClientName = clientRepository.findByClientName(clientServiceDto.getClientName());
        Client client = byClientName != null && !byClientName.isEmpty() ? byClientName.get(0) : new Client();

        client.setClientName(clientServiceDto.getClientName());
        client.setActive(clientServiceDto.getActive() != null ? clientServiceDto.getActive() : client.getActive());
        client.setTokenDuration(clientServiceDto.getTokenDuration() != null ? clientServiceDto.getTokenDuration() : client.getTokenDuration());
        client.setTokenRenewable(clientServiceDto.getTokenRenewable() != null ? clientServiceDto.getTokenRenewable() : client.getTokenRenewable());
        client.setAlwaysRenew(clientServiceDto.getAlwaysRenew() != null ? clientServiceDto.getAlwaysRenew() : client.getAlwaysRenew());

        client = clientRepository.save(client);

        ClientServiceDto responseDto = new ClientServiceDto();
        responseDto.setId(client.getId());
        responseDto.setClientName(client.getClientName());
        responseDto.setActive(client.getActive());
        responseDto.setTokenDuration(client.getTokenDuration());
        responseDto.setTokenRenewable(client.getTokenRenewable());
        responseDto.setAlwaysRenew(client.getAlwaysRenew());
        responseDto.setCreatedDate(client.getCreatedDate());

        return responseDto;
    }

    Client safelyGetClientByClientName(String clientName) {
        List<Client> byClientName = clientRepository.findByClientName(clientName);
        Preconditions.checkState(byClientName != null && !byClientName.isEmpty(), "No client found for the informed client name");
        return byClientName.get(0);
    }

    Client safelyGetClient(String clientId) {
        Preconditions.checkArgument(clientId != null, "clientId is mandatory");

        Client client = clientRepository.findOne(clientId);
        Preconditions.checkState(client != null, "Client not found");

        return client;
    }
}
