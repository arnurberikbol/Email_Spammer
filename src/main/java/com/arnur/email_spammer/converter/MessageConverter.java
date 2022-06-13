package com.arnur.email_spammer.converter;

import com.arnur.email_spammer.dto.MessageDto;
import com.arnur.email_spammer.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageConverter {

    public static MessageDto entityToDto(Message message) {
        MessageDto messageDto = new MessageDto();

        messageDto.setId(message.getId());
        messageDto.setGroup(message.getGroup());
        messageDto.setName(message.getGroup().getName());
        messageDto.setSubject(message.getSubject());
        messageDto.setText(message.getText());
        messageDto.setDate(message.getDate());

        return messageDto;
    }

    public static List<MessageDto> entityToDto(List<Message> message) {
        return message.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public static Message dtoToEntity(MessageDto messageDto) {
        Message message = new Message();

        message.setId(messageDto.getId());
        message.setGroup(messageDto.getGroup());
        message.setSubject(messageDto.getSubject());
        message.setText(messageDto.getText());
        message.setDate(messageDto.getDate());

        return message;
    }

    public static List<Message> dtoToEntity(List<MessageDto> messageDto) {
        return messageDto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
