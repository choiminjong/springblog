package com.springblog.springblog.controller;

import com.springblog.springblog.model.Users;
import com.springblog.springblog.repository.UserRepository;
import com.springblog.springblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccoutController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/register")
    public String register(Users users){ ;
        return "account/register";
    }

    @PostMapping("/register")
    public String registers(Users users){
        userService.save(users);
        return "redirect:/account/login";
    }
}
