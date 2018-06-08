package test.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import test.Message;
import test.repositories.MessageRepository;
import test.utils.TUtils;

@Controller
public class MessagingController {
	
	@Autowired
    MessageRepository repository;

    @MessageMapping("/message")
    @SendTo("/test/messages")
    public List<Message> messaging(Message message) throws Exception {
    	message.setContent(TUtils.reverse(message.getContent()));
    	repository.save(message);
    	Iterable<Message>list = repository.findAll();
    	List<Message> messages = new ArrayList<Message>();
    	for (Message msg : list) {
			messages.add(msg);
		}
    	return messages;
    }

}
