package com.arnur.email_spammer.service;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}

