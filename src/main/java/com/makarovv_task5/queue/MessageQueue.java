package com.makarovv_task5.queue;

import com.makarovv_task5.model.Message;
import com.makarovv_task5.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MessageQueue {
    @Autowired
    private MessageRepository messageRepository;


    public MessageQueue() {
    }

    public List<Message> all() {
        List<Message> messageQueue = new ArrayList((Collection<Message>) messageRepository.findAll());
        return messageQueue;
    }

    public void offer(Message message) {
        messageRepository.save(message);
    }

    public Message peek() {
        Message message = messageRepository.findFirstByIdNotNullOrderByIdAsc().orElse(new Message());
        return message;
    }

    public Message poll() {
        Message message = messageRepository.findFirstByIdNotNullOrderByIdAsc().orElse(new Message());
        messageRepository.delete(message);
        return message;
    }

    public Message peekMax() {
        Message message = messageRepository.findFirstByIdNotNullOrderByIdDesc().orElse(new Message());
        return message;
    }
}
