package com.springblog.springblog.controller;

import com.springblog.springblog.model.Board;
import com.springblog.springblog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                    @RequestParam(required = false, defaultValue = "") String content){

        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardRepository.findAll();
        }else{
            return boardRepository.findByTitleOrContent(title,content);
        }
    }

    @PostMapping("/boards")
    Board newBoards(@RequestBody Board newBoard){
        return boardRepository.save(newBoard);
    }

    @GetMapping("/boards/{id}")
    Board One(@PathVariable Long id){
        return boardRepository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {
            return boardRepository.findById(id)
                .map(board -> {
                        board.setTitle(newBoard.getTitle());
                        board.setContent(newBoard.getContent());
                        return boardRepository.save(board);
        })
        .orElseGet( () ->{
            newBoard.setId(id);
            return boardRepository.save(newBoard);

        });
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id){
        System.out.println("id = " + id);
        boardRepository.deleteById(id);
    }

}
