package com.arnur.email_spammer.dao;

import com.arnur.email_spammer.entity.Client;

import java.util.List;

public interface ClientDao {
    public List<Client> getAllClients();

    public void saveClient(Client client);

    public void persistClient(Client client);

    public Client getClient(int id);

    public void deleteClient(int id);

    public List<Client> searchClients(String name);

    public List<Client> exceptThem(List<Client> clients);
}