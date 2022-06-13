package com.arnur.email_spammer.service;


import com.arnur.email_spammer.dto.ClientDto;
import com.arnur.email_spammer.dto.GroupDto;

import java.util.List;

public interface GroupService {
    public List<GroupDto> getAllGroups();

    public List<GroupDto> getAllGroupsWithClients();

    public boolean saveGroup(GroupDto groupDto);

    public GroupDto getGroup(int id);

    public void deleteGroup(int id);

    public void addClient(int id, ClientDto clientDto);

    public List<GroupDto> searchGroup(String name);

    public List<ClientDto> getAllClients(int id);

    public void removeClient(int id, ClientDto clientDto);

    public boolean sendEmail(int id, String subject, String text);

}
