package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/main_page")
public class MainController {

    private final UserService service;

    @Autowired
    public MainController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getRole(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);

        model.addAttribute("roles", service.getAllRoles());
        return "/main_page";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(required = false) List<Long> roles, Model model) {

        user.setRoles(service.findAllById(roles));
        service.saveUpdateUser(user);
        return "redirect:/main_page";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        service.deleteUser(id);
        return "redirect:/main_page";
    }
}
