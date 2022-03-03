package com.springblog.springblog.controller;

import com.springblog.springblog.model.Board;
import com.springblog.springblog.model.Users;
import com.springblog.springblog.repository.BoardRepository;
import com.springblog.springblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersApiController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<Users> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    Users newUser(@RequestBody Users newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/users/{id}")
    Users one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    Users replaceUser(@RequestBody Users newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
