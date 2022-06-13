package com.arnur.email_spammer.converter;

import com.arnur.email_spammer.dto.ClientDto;
import com.arnur.email_spammer.entity.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientConverter {

    public static ClientDto entityToDto(Client client) {
        ClientDto clientDto = new ClientDto();

        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setSurname(client.getSurname());
        clientDto.setEmail(client.getEmail());
        clientDto.setGroups(client.getGroups());

        return clientDto;
    }

    public static List<ClientDto> entityToDto(List<Client> client) {
        return client.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public static Client dtoToEntity(ClientDto clientDto) {
        Client client = new Client();

        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setSurname(clientDto.getSurname());
        client.setEmail(clientDto.getEmail());
        client.setGroups(clientDto.getGroups());

        return client;
    }

    public static List<Client> dtoToEntity(List<ClientDto> clientDto) {
        return clientDto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

}
