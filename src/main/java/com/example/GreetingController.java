package com.example;

/**
 * Created by goodjwon on 2015. 12. 19..
 */
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.model.Greeting;
import com.example.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private MessageService messageService;

    @RequestMapping(method= RequestMethod.GET)
    public List<Message> getMessage(){
        return messageService.getAllMessage();
    }

    @RequestMapping(method= RequestMethod.POST)
    public Message addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }

    @RequestMapping(method= RequestMethod.PUT, value = "/{messageId}")
    public Message updateMessage(@PathVariable long messageId,  @RequestBody Message message){
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/{messageId}")
    public void deleteMessage(@PathVariable long messageId){
        messageService.removeMessage(messageId);
    }

    @RequestMapping("/{messageId}")
    public Message getMessage(@PathVariable long messageId){

        return  messageService.getMessage(messageId);
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }


}
