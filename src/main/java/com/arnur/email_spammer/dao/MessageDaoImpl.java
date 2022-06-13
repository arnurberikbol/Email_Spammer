package com.arnur.email_spammer.dao;

import com.arnur.email_spammer.entity.Group;
import com.arnur.email_spammer.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Message> getAllMessages() {
        Query query = entityManager.createQuery("from Message order by id desc");
        List<Message> allMessage = query.getResultList();
        return allMessage;
    }

    @Override
    public void saveMessage(Group group, String subject, String text) {
        Message message = new Message();
        message.setGroup(group);
        message.setSubject(subject);
        message.setText(text);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        message.setDate(dtf.format(now));
        entityManager.merge(message);
    }

    @Override
    public Message getMessage(int id) {
        return entityManager.find(Message.class,id);
    }

    @Override
    public void deleteMessage(int id) {
        Query query = entityManager.createQuery("delete from Message " +
                "where id =:MessageId");
        query.setParameter("MessageId",id);
        query.executeUpdate();
    }

    @Override
    public Group getGroup(int id) {
        Message message = entityManager.find(Message.class,id);
        return message.getGroup();
    }
}
