package org.example.blogproject.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.post.service.LikeService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
}
