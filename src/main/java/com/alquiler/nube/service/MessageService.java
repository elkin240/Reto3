package com.alquiler.nube.service;

import com.alquiler.nube.entities.Message;
import com.alquiler.nube.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

      @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getProduct(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message m) {
        if (m.getIdMessage() == null) {
            return messageRepository.save(m);
        } else {
            Optional<Message> e = messageRepository.getMessage(m.getIdMessage());
            if (e.isPresent()) {
                return m;
            } else {
                return messageRepository.save(m);
            }
        }
    }

    public Message update(Message m) {
        if (m.getIdMessage() != null) {
            Optional<Message> q = messageRepository.getMessage(m.getIdMessage());
            if (q.isPresent()) {
                if (m.getMessageText() != null) {
                    q.get().setMessageText(m.getMessageText());
                }
                if (m.getClient() != null) {
                    q.get().setClient(m.getClient());
                }

                if (m.getCloud() != null) {
                    q.get().setCloud(m.getCloud());
                }

                messageRepository.save(q.get());
                return q.get();
            } else {
                return m;
            }
        } else {
            return m;
        }
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Message> p = messageRepository.getMessage(id);
        if (p.isPresent()) {
            messageRepository.delete(p.get());
            flag = true;
        }
        return flag;

    }


}
