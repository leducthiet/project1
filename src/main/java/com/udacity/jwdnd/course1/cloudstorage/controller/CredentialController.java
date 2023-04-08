package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CredentialController {
    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping("/credential")
    public String credential(Authentication authentication, Model model, Credential credential) {

        User appUser = userService.getUser(authentication.getName());
        Integer userId = appUser.getUserId();
        credential.setUserId(userId);

        String credentialError = "";

        if (credential.getCredentialId() == null) {
            int credentialCount = credentialService.insertCredential(credential);

            if (credentialCount < 1) {
                credentialError = "An error has occurred while adding credential. Please try again.";
            }

            if (credentialError.equals("")) {
                model.addAttribute("successMsg", "Credential has been added successfully.");
            } else {
                model.addAttribute("errorMsg", credentialError);
            }
        } else {
            int credentialCount = credentialService.updateCredential(credential);

            if (credentialCount < 1) {
                credentialError = "An error has occurred while editing credential. Please try again.";
            }

            if (credentialError.equals("")) {
                model.addAttribute("successMsg", "Credential has been updated successfully.");
            } else {
                model.addAttribute("errorMsg", credentialError);
            }
        }
        return "result";
    }

    @RequestMapping(value = "/deleteCredential/{credentialId}", method = RequestMethod.GET)
    public String deleteCredential(@PathVariable Integer credentialId, Model model) {

        String credentialError = "";

        int credentialCount = credentialService.deleteCredential(credentialId);

        if (credentialCount < 1) {
            credentialError = "An error has occurred while deleting. Please try again.";
        }

        if (credentialError.equals("")) {
            model.addAttribute("successMsg", "Credential has been deleted successfully.");
        } else {
            model.addAttribute("errorMsg", credentialError);
        }
        return "result";
    }
}
