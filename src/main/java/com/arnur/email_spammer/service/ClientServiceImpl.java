package com.arnur.email_spammer.service;

import com.arnur.email_spammer.dao.ClientDao;
import com.arnur.email_spammer.dto.ClientDto;
import com.arnur.email_spammer.converter.ClientConverter;
import com.arnur.email_spammer.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ClientConverter clientConverter;

    @Override
    @Transactional
    public List<ClientDto> getAllClients() {
        List<Client> allClients = clientDao.getAllClients();
        return clientConverter.entityToDto(allClients);
    }


    @Override
    public boolean saveClient(ClientDto clientDto) {
        try {
            Client client = clientConverter.dtoToEntity(clientDto);
            clientDao.saveClient(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean persistClient(ClientDto clientDto) {
        Client client = clientConverter.dtoToEntity(clientDto);
        try {
            clientDao.persistClient(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    @Transactional
    public ClientDto getClient(int id) {
        Client client = clientDao.getClient(id);
        return clientConverter.entityToDto(client);
    }


    @Override
    @Transactional
    public void deleteClient(int id) {
        clientDao.deleteClient(id);
    }

    @Override
    @Transactional
    public List<ClientDto> searchClient(String name) {
        List<Client> allClients = clientDao.searchClients(name);
        return clientConverter.entityToDto(allClients);
    }

    @Override
    @Transactional
    public List<ClientDto> exceptThem(List<ClientDto> clientsDto) {
        List<Client> otherClients = clientConverter.dtoToEntity(clientsDto);
        List<Client> finalClients = clientDao.exceptThem(otherClients);
        return clientConverter.entityToDto(finalClients);
    }
}
