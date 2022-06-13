package com.arnur.email_spammer.service;

import com.arnur.email_spammer.converter.GroupConverter;
import com.arnur.email_spammer.converter.MessageConverter;
import com.arnur.email_spammer.dao.MessageDao;
import com.arnur.email_spammer.dto.MessageDto;
import com.arnur.email_spammer.entity.Group;
import com.arnur.email_spammer.dto.GroupDto;
import com.arnur.email_spammer.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private GroupConverter groupConverter;

    @Override
    @Transactional
    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageDao.getAllMessages();
        return messageConverter.entityToDto(messages);
    }

    @Override
    @Transactional
    public void saveMessage(GroupDto groupDto, String subject, String text) {
        Group group = groupConverter.dtoToEntity(groupDto);
        messageDao.saveMessage(group,subject,text);
    }

    @Override
    @Transactional
    public MessageDto getMessage(int id) {
        Message message = messageDao.getMessage(id);
        return messageConverter.entityToDto(message);
    }

    @Override
    @Transactional
    public void deleteMessage(int id) {
        messageDao.deleteMessage(id);
    }

    @Override
    @Transactional
    public String getName(int id) {
        Group group = messageDao.getGroup(id);
        return group.getName();
    }
}
