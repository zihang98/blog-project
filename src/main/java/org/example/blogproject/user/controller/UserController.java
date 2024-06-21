package org.example.blogproject.user.controller;

import org.example.blogproject.user.domain.User;
import org.example.blogproject.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showUserRegForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String userReg(@ModelAttribute("user") User user, Model model) {

        userService.saveUser(user);

        model.addAttribute("message", user.toString() + "회원가입 완료");

        return "signupsuccess";
    }

    @GetMapping("/check-duplicate")
    @ResponseBody
    public boolean checkDuplicate(@RequestParam("id") String userName) {
        System.out.println(userService.isDuplicate(userName));
        return userService.isDuplicate(userName);
    }
}
