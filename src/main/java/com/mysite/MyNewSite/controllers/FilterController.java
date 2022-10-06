package com.mysite.MyNewSite.controllers;

import com.mysite.MyNewSite.entity.Message;
import com.mysite.MyNewSite.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilterController {

    @Autowired
    private MessageRepository messageRepository;


    @PostMapping("/filter")
    public String filter(@RequestParam String filter,
                         Model model){
        Iterable<Message> messages;

        if (filter != null && filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        } else {
           messages = messageRepository.findAll();
        }
        model.addAttribute("messages",messages);
        return "filter";
    }
}
