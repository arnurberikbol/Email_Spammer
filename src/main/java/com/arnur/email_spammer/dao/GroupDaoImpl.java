package com.arnur.email_spammer.dao;

import com.arnur.email_spammer.entity.Client;
import com.arnur.email_spammer.entity.Group;
import com.arnur.email_spammer.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Group> getAllGroups() {

        Query query = entityManager.createQuery("from Group");
        List<Group> allGroups = query.getResultList();
        System.out.println("get all group");
        return allGroups;
    }

    @Override
    @Transactional
    public void saveGroup(Group group) {
        try {
            group.setClients(entityManager.find(Group.class, group.getId()).getClients());
        } catch (Exception e) {}
        entityManager.merge(group);
        System.out.println("save group");
    }

    @Override
    public void persistGroup(Group group) {
        entityManager.persist(group);
        System.out.println("persist group");

    }

    @Override
    public Group getGroup(int id) {
        Group group = entityManager.find(Group.class,id);
        System.out.println("get group");
        return group;
    }

    @Override
    public void deleteGroup(int id) {
        Query query = entityManager.createQuery("delete from Message " +
                "where group =:Group");
        query.setParameter("Group",entityManager.find(Group.class,id));
        query.executeUpdate();

        query = entityManager.createQuery("delete from Group " +
                "where id =:GroupId");
        System.out.println("-----------------------------");
        query.setParameter("GroupId",id);
        query.executeUpdate();
        System.out.println("delete group");
    }

    @Override
    public void addClientToGroup(int id, Client client) {
        Group group = entityManager.find(Group.class,id);
        group.addClientToGroup(client);
        entityManager.merge(group);
        System.out.println("add client to group");
    }

    @Override
    public List<Client> allClients(int id) {
        Group group = entityManager.find(Group.class,id);
        System.out.println("all clients of group");
        return group.getClients();
    }

    @Override
    public void removeCLient(int id, Client client) {
        Group group = entityManager.find(Group.class,id);
        group.removeClientFromGroup(client);
        client.removeClientFromGroup(group);
        entityManager.merge(group);
        System.out.println("remove client from group");
    }

    @Override
    @Transactional
    public void sendEmail(int id, String subject, String text) {
        Group group = entityManager.find(Group.class,id);
        List<Client> clients =  group.getClients();

        for (Client client : clients) {
            String email = client.getEmail();
            emailService.sendSimpleMessage(email, subject, text);
        }
        System.out.println("send email to group");
    }

    @Override
    public List<Group> searchGroups(String name) {
        Query query = entityManager.createQuery("from Group " +
                "where name like '" + name + "%'");
        List<Group>  allGroup = query.getResultList();
        System.out.println("search group");
        return allGroup;
    }
}
