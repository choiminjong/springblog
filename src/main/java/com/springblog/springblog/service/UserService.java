package com.springblog.springblog.service;

import com.springblog.springblog.model.Roles;
import com.springblog.springblog.model.Users;
import com.springblog.springblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users save(Users users) {
        String encodePassword = passwordEncoder.encode(users.getPassword());

        users.setPassword(encodePassword);
        users.setEnabled(true);

        // 하드코딩한 Role 데이터베잇, ID를 사용하기위해 넣었습니다.
        Roles roles = new Roles();
        roles.setId(1l);

        users.getRoles().add(roles);

        return userRepository.save(users);
    }
}
