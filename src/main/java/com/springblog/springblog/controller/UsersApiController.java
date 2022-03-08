package com.springblog.springblog.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springblog.springblog.dto.UserDTO;
import com.springblog.springblog.model.Board;
import com.springblog.springblog.model.Users;
import com.springblog.springblog.repository.UserRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UsersApiController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository repository;

    @GetMapping("/users") List<Users> all() {
        List<Users> users = repository.findAll();
        log.debug("getBoards().size() 호출전");
        log.debug("getBoards().size() : {}",users.get(0).getBoards().size());
        log.debug("getBoards().size() 호출 후");
        return users;
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
                .map(users -> {
                    users.getBoards().clear();
                    users.getBoards().addAll(newUser.getBoards());
                    for(Board board : users.getBoards()) {
                        System.out.println("board = " + board.getId());
                        if(board.getId() != null){
                            board.setUsers(users);
                        }
                    }
                    return repository.save(users);
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
