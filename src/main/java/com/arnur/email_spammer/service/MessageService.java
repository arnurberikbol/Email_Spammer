package com.arnur.email_spammer.service;

import com.arnur.email_spammer.dto.MessageDto;
import com.arnur.email_spammer.dto.GroupDto;

import java.util.List;

public interface MessageService {
    public List<MessageDto> getAllMessages();

    public void saveMessage(GroupDto groupDto, String subject, String text);

    public MessageDto getMessage(int id);

    public void deleteMessage(int id);

    public String getName(int id);
}
