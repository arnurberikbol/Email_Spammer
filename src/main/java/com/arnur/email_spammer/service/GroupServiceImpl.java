package com.arnur.email_spammer.service;

import com.arnur.email_spammer.dto.ClientDto;
import com.arnur.email_spammer.converter.ClientConverter;
import com.arnur.email_spammer.converter.GroupConverter;
import com.arnur.email_spammer.dao.GroupDao;
import com.arnur.email_spammer.dto.GroupDto;
import com.arnur.email_spammer.entity.Client;
import com.arnur.email_spammer.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private GroupConverter groupConverter;

    @Override
    @Transactional
    public List<GroupDto> getAllGroups() {
        List<Group> allGroups = groupDao.getAllGroups();
        return groupConverter.entityToDto(allGroups);
    }

    @Override
    public List<GroupDto> getAllGroupsWithClients() {
        List<Group> allGroups = groupDao.getAllGroups();
        List<Group> finalAllGroups = new ArrayList<>();

        for (Group group : allGroups) {
            if (!group.getClients().isEmpty()) {
                finalAllGroups.add(group);
            }
        }

        return groupConverter.entityToDto(finalAllGroups);
    }


    @Override
    public boolean saveGroup(GroupDto groupDto) {
        try {
            Group group = groupConverter.dtoToEntity(groupDto);
            groupDao.saveGroup(group);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    @Transactional
    public GroupDto getGroup(int id) {
        Group group = groupDao.getGroup(id);
        return groupConverter.entityToDto(group);
    }


    @Override
    @Transactional
    public void deleteGroup(int id) {
        groupDao.deleteGroup(id);

    }

    @Override
    @Transactional
    public void addClient(int id, ClientDto clientDto) {
        Client client = clientConverter.dtoToEntity(clientDto);
        groupDao.addClientToGroup(id,client);
    }

    @Override
    public List<GroupDto> searchGroup(String name) {
        List<Group> groups = groupDao.searchGroups(name);
        return groupConverter.entityToDto(groups);
    }

    @Override
    @Transactional
    public List<ClientDto> getAllClients(int id) {
        List<Client> clients = groupDao.allClients(id);
        return clientConverter.entityToDto(clients);
    }

    @Override
    @Transactional
    public void removeClient(int id, ClientDto clientDto) {
        Client client = clientConverter.dtoToEntity(clientDto);
        groupDao.removeCLient(id, client);
    }

    @Override
    public boolean sendEmail(int id, String subject, String text) {
        try {
            groupDao.sendEmail(id, subject, text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
