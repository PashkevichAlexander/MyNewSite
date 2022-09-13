package com.mysite.MyNewSite.controllers;

import com.mysite.MyNewSite.domian.Message;
import com.mysite.MyNewSite.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddController {
    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("main")
    public String add(
            @RequestParam String text,
            @RequestParam String tag,
            Model model){
        final Message message = new Message(text, tag);

        messageRepository.save(message);

        final Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }
}
