package org.example.blogproject.post.repository;

import org.example.blogproject.post.domain.Comment;
import org.example.blogproject.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByUserId(Long userId);
}
