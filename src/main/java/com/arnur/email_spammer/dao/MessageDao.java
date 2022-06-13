package com.arnur.email_spammer.dao;

import com.arnur.email_spammer.entity.Group;
import com.arnur.email_spammer.entity.Message;

import java.util.List;

public interface MessageDao {
    public List<Message> getAllMessages();

    public void saveMessage(Group group, String subject, String text);

    public Message getMessage(int id);

    public void deleteMessage(int id);

    public Group getGroup(int id);
}
