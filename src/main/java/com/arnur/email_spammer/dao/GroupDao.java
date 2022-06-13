package com.arnur.email_spammer.dao;

import com.arnur.email_spammer.entity.Client;
import com.arnur.email_spammer.entity.Group;


import java.util.List;

public interface GroupDao {


    public List<Group> getAllGroups();

    public void saveGroup(Group group);

    public void persistGroup(Group group);

    public Group getGroup(int id);

    public void deleteGroup(int id);

    public void addClientToGroup(int id, Client client);

    public List<Client> allClients(int id);

    public void removeCLient(int id, Client client);

    public void sendEmail(int id, String subject, String text);

    List<Group> searchGroups(String name);
}
