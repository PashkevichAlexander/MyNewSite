package com.mysite.MyNewSite.controllers;

import com.mysite.MyNewSite.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccountPageController {

    @GetMapping("/userAccountPage")
    public String userPage(@AuthenticationPrincipal User user,
                           Model model){
        model.addAttribute("user", user);
        return "userAccountPage";
    }
}
