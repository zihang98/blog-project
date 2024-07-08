package org.example.blogproject.post.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.blogproject.post.domain.Post;
import org.example.blogproject.post.service.CommentService;
import org.example.blogproject.post.service.PostService;
import org.example.blogproject.user.domain.User;
import org.example.blogproject.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/mypage")
    public String showMyPage(HttpServletRequest request, Model model) {
        String userName = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userName")) {
                    userName = cookie.getValue();
                    break;
                }
            }
        }

        if (userName != null) {
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);

            List<Post> posts = postService.findByUserId(user.getId());
            model.addAttribute("posts", posts);
        }

        return "mypage";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("post", new Post());
        return "write";
    }

    @PostMapping("/write")
    public String savePost(@ModelAttribute("post") Post post, HttpServletRequest request, @RequestParam(name = "isTemp") boolean isTemp) {
        String userName = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userName")) {
                userName = cookie.getValue();
                break;
            }
        }

        User user = userService.findByUserName(userName);
        post.setUser(user);
        post.setTemp(isTemp);
        postService.savePost(post);
        return "redirect:/mypage";
    }
}
