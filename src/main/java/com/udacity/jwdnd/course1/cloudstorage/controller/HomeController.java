package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    private UserService userService;

    private CredentialService credentialService;

    private EncryptionService encryptionService;

    public HomeController(UserService userService, CredentialService credentialService,
                          EncryptionService encryptionService) {
        this.userService=userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping(value = {"/home"})
    public String getHome(Authentication authentication,
                          Model model){
        User appUser = userService.getUser(authentication.getName());
        Integer userId = appUser.getUserId();

        model.addAttribute("credentials",credentialService.getAllCredentialByUser(userId));
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }
}
