package com.mysite.MyNewSite.controllers;

import com.mysite.MyNewSite.dto.UserDTO;
import com.mysite.MyNewSite.entity.User;
import com.mysite.MyNewSite.repositories.UserRepository;
import com.mysite.MyNewSite.services.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;



@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    private final UserDTOService userDTOService;
    private final BCryptPasswordEncoder encoder;

    public RegistrationController(UserDTOService userDTOService, BCryptPasswordEncoder encoder) {
        this.userDTOService = userDTOService;
        this.encoder = encoder;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(ModelMap modelMap) {
        modelMap.addAttribute("user", userDTOService.create());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult br, Model model) {
        if (userDTOService.existByUsername(user.getUsername())) br.rejectValue("username", "", "Этот логин уже занят");
        if (br.hasErrors()) {
            return "registration";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userDTOService.add(user);
        return "registrationSuccess";
    }
}