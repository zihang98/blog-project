package org.example.blogproject.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/loginform")
    public String loginForm() {
        return "loginform";
    }

//    @GetMapping("userregform")
//    public String userRegForm() {
//        return "signform";
//    }
}
