package com.smartgrow.authentication.facade;

import com.smartgrow.authentication.facade.dto.ClientFacadeDto;
import com.smartgrow.authentication.service.ClientService;
import com.smartgrow.authentication.service.dto.ClientServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ClientFacade {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public Object createClient(@Valid @RequestBody ClientFacadeDto payload) {
        ClientServiceDto clientServiceDto = new ClientServiceDto();
        clientServiceDto.setClientName(payload.getClientName());
        clientServiceDto.setActive(payload.getActive());
        clientServiceDto.setTokenDuration(payload.getTokenDuration());
        clientServiceDto.setTokenRenewable(payload.getTokenRenewable());
        clientServiceDto.setAlwaysRenew(payload.getAlwaysRenew());

        return clientService.save(clientServiceDto);
    }
}
