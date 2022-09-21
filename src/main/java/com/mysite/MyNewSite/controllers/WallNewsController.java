package com.mysite.MyNewSite.controllers;

import com.mysite.MyNewSite.entity.Message;
import com.mysite.MyNewSite.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class WallNewsController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/wall")
    public String main( Model model) {

        final Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);
        return "wall";
    }

}
