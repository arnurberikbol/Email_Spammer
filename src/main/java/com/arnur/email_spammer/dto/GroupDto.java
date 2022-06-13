package com.arnur.email_spammer.dto;

import com.arnur.email_spammer.entity.Client;
import com.arnur.email_spammer.entity.Message;

import java.util.List;

public class GroupDto {
    private int id;
    private String name;
    private List<Client> clients;
    private List<Message> messages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
