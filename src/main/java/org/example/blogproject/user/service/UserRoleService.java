package org.example.blogproject.user.service;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.user.domain.UserRole;
import org.example.blogproject.user.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}
