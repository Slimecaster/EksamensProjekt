package com.example.eksamensprojekt.Controller;

import com.example.eksamensprojekt.Model.MyUser;
import com.example.eksamensprojekt.Repository.DBcontroller;
import com.example.eksamensprojekt.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
    private final CustomUserDetailsService userDetailsService;
    private final DBcontroller dbcontroller;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebController(CustomUserDetailsService userDetailsService, DBcontroller dbcontroller, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.dbcontroller = dbcontroller;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }
    @GetMapping("/admin/homepage")
    public String adminHome() {
        return "homepage_admin";
    }

    @GetMapping("/user/homepage")
    public String userHome() {
        return "homepage_user";
    }

    @GetMapping("/login/register/user")
    public String registerUser(Model model){
        model.addAttribute("MyUser", new MyUser());
        return "registerUser";
    }

    @PostMapping("/login/register/user")
    public String registerUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dbcontroller.createUpdateUser(user);
        return "redirect:/login/register/user/userCreatedSuccess";

    }
    @GetMapping("/login/register/user/userCreatedSuccess")
    public String userCreatedSuccess() {
        return "userCreatedSuccess";
    }
}

