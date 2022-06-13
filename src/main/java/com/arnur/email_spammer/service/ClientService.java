package com.arnur.email_spammer.service;

import com.arnur.email_spammer.dto.ClientDto;

import java.util.List;

public interface ClientService {
    public List<ClientDto> getAllClients();

    public boolean saveClient(ClientDto clientDto);

    public boolean persistClient(ClientDto clientDto);

    public ClientDto getClient(int id);

    public void deleteClient(int id);

    public List<ClientDto> searchClient(String name);

    public List<ClientDto> exceptThem(List<ClientDto> clientsDto);
}
