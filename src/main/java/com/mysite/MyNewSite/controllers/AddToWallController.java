package com.mysite.MyNewSite.controllers;

import com.mysite.MyNewSite.entity.Message;
import com.mysite.MyNewSite.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddToWallController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("wall")
    public String add(
            @RequestParam String text,
            @RequestParam String tag,
            Model model
    ) {
        final Message message = new Message(text, tag);

        messageRepository.save(message);

        final Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "wall";
    }
}
