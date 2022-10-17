package com.mysite.MyNewSite.controllers;

import com.mysite.MyNewSite.entity.Message;
import com.mysite.MyNewSite.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteMessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/deleteMessage")
    public String deleteMessage(int id){
           messageRepository.deleteById(id);
        return "redirect:/main";
    }
}
