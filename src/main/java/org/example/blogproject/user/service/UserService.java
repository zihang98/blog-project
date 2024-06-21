package org.example.blogproject.user.service;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.user.domain.User;
import org.example.blogproject.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean isDuplicate(String userName) { // 회원가입 시 id중복 체크
        return userRepository.findByUserName(userName) != null;
    }

    @Transactional(readOnly = true)
    public boolean isAuthenticated(String userName, String password) { // 로그인 시 id, pw 체크
        if (userRepository.findByUserName(userName) != null) {
            if (password.equals(userRepository.findByUserName(userName).getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
