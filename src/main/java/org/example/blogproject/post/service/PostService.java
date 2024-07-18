package org.example.blogproject.post.service;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.post.domain.Post;
import org.example.blogproject.post.repository.PostRepository;
import org.example.blogproject.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public void plusCommentCount(Post post) {
        post.setCommentCount(post.getCommentCount() + 1);
    }
}
