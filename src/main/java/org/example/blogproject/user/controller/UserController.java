package org.example.blogproject.user.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.blogproject.user.domain.User;
import org.example.blogproject.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(@CookieValue(name = "userName", required = false) String userName, Model model) {

        if (userName == null) {
            return "home";
        }

        User loggedUser = userService.findByUserName(userName);

        model.addAttribute("loggedUser", loggedUser);

        return "afterloginhome";
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
        return userService.isDuplicate(userName);
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "loginform";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, Model model, HttpServletResponse response) {
        if (userService.isAuthenticated(userName, password)) {
            Cookie cookie = new Cookie("userName", userName);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);

            response.addCookie(cookie);

            return "redirect:/home";
        } else {
            model.addAttribute("error", "id나 비밀번호가 틀렸습니다");
            return "loginform";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("userName", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/home";
    }
}
