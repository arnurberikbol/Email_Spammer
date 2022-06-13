package com.arnur.email_spammer.dao;

import com.arnur.email_spammer.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Client> getAllClients() {

        Query query = entityManager.createQuery("from Client");
        List<Client> allClient = query.getResultList();
        System.out.println("get all client");
        return allClient;
    }

    @Override
    @Transactional
    public void saveClient(Client client) {
        client.setGroups(entityManager.find(Client.class,client.getId()).getGroups());
        entityManager.merge(client);
        System.out.println("save client");
    }

    @Override
    @Transactional
    public void persistClient(Client client) {
        entityManager.persist(client);
        System.out.println("persist client");
    }

    @Override
    public Client getClient(int id) {
        Client Client = entityManager.find(Client.class,id);
        System.out.println("get client");
        return Client;
    }

    @Override
    public void deleteClient(int id) {
        Query query = entityManager.createQuery("delete from Client " +
                "where id =:ClientId");
        query.setParameter("ClientId",id);
        query.executeUpdate();
        System.out.println("delete client");
    }

    @Override
    public List<Client> searchClients(String name) {
        String[] splited = name.split(" ");
        Set<Client> allClient = new LinkedHashSet<Client>();
        if (splited.length == 2) {
            Query query = entityManager.createQuery("from Client " +
                    "where name like '%" + splited[0] + "%' and surname like '%" + splited[1] + "%'");
            List<Client> clients1 = query.getResultList();
            allClient.addAll(clients1);

            query = entityManager.createQuery("from Client " +
                    "where name like '%" + splited[1] + "%' and surname like '%" + splited[0] + "%'");
            List<Client> clients2 = query.getResultList();

            allClient.addAll(clients2);
        }
        if (splited.length == 1) {
            Query query = entityManager.createQuery("from Client " +
                    "where name like '%" + splited[0] + "%'");
            List<Client> clients1 = query.getResultList();
            allClient.addAll(clients1);

            query = entityManager.createQuery("from Client " +
                    "where surname like '%" + splited[0] + "%'");
            List<Client> clients2 = query.getResultList();

            allClient.addAll(clients2);
        }

        List<Client> finalClient = new ArrayList<>(allClient);
        System.out.println("search client");
        return finalClient;
    }

    @Override
    public List<Client> exceptThem(List<Client> clients) {
        Query query = entityManager.createQuery("from Client");
        List<Client> allClient = query.getResultList();
        List<String> emails = new ArrayList<>();
        List<Client> finalClient = new ArrayList<Client>();

        for (Client client : clients) {
            emails.add(client.getEmail());
        }

        for (Client client : allClient) {
            if (!emails.contains(client.getEmail())) {
                finalClient.add(client);
            }
        }

        System.out.println("except client");
        return finalClient;
    }
}