package com.example.financetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/")
    public String home() {
        return "redirect:http://localhost:5173/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "redirect:http://localhost:5173/login";
    }

}