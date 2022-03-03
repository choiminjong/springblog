package com.springblog.springblog.service;

import com.springblog.springblog.model.Board;
import com.springblog.springblog.model.Users;
import com.springblog.springblog.repository.BoardRepository;
import com.springblog.springblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    public Board save(String username, Board board) {
        Users user = userRepository.findByUsername(username);
        board.setUsers(user);
        return boardRepository.save(board);
    }
}
