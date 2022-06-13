package com.arnur.email_spammer.converter;


import com.arnur.email_spammer.dto.GroupDto;
import com.arnur.email_spammer.entity.Group;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupConverter {
    public static GroupDto entityToDto(Group group) {
        GroupDto groupDto = new GroupDto();

        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setClients(group.getClients());
        groupDto.setMessages(group.getMessages());

        return groupDto;
    }

    public static List<GroupDto> entityToDto(List<Group> group) {
        return group.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public static Group dtoToEntity(GroupDto groupDto) {
        Group group = new Group();

        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setClients(groupDto.getClients());
        group.setMessages(groupDto.getMessages());

        return group;
    }

    public static List<Group> dtoToEntity(List<GroupDto> groupDto) {
        return groupDto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
