package com.example;

import com.example.database.DatabaseClass;
import com.example.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by goodjwon on 2015. 12. 24..
 */
@Service("MessageService")
public class MessageService {


    private Map<Long, Message> messages = DatabaseClass.getMessages();


    public MessageService() {
        messages.put(1L, new Message(1, "Hello World", "goodjwon"));
        messages.put(2L, new Message(2, "Hello RESTful", "goodjwon"));
    }

    public List<Message> getAllMessage(){
        return new ArrayList<Message>(messages.values());
    }

    public Message getMessage(long id){
        return messages.get(id);
    }

    public Message addMessage(Message message){
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message){
        if(message.getId() <= 0){
            return  null;
        }
        messages.put(message.getId(), message);
        return  message;
    }

    public Message removeMessage(long id){
        return messages.remove(id);
    }
}
