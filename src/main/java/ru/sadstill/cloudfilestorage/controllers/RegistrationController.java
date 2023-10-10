package ru.sadstill.cloudfilestorage.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sadstill.cloudfilestorage.DTOs.UserDTO;
import ru.sadstill.cloudfilestorage.services.security.UserDetailsServiceImpl;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/registration")
    private String showRegistrationPage(@ModelAttribute("user") UserDTO user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult bindingResult,
                               Model model) {

        try {
//            User
        }

    }

}
