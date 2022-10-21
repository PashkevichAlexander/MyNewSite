package com.mysite.MyNewSite.controllers;

import com.mysite.MyNewSite.entity.Message;
import com.mysite.MyNewSite.entity.User;
import com.mysite.MyNewSite.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AddMessageController {
    @Autowired
    private MessageRepository messageRepository;

    @Value("$upload.path")
    private String uploadPath;

    @PostMapping("/addMessage")
    public String add(
            @AuthenticationPrincipal User user,

            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {

        final Message message = new Message(text, tag, user.getUsername());

        if (file != null) {
            final File uploaderDirectory = new File(uploadPath);
            if (!uploaderDirectory.exists()) {
                uploaderDirectory.mkdir();
            }
            final String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(resultFileName));

            message.setFilename(resultFileName);
        }


        messageRepository.save(message);

        final Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }
}
