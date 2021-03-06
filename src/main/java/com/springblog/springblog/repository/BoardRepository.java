package com.springblog.springblog.repository;

import com.springblog.springblog.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title,String content);

    // Title , Content like 검색을 할 수 있다.
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
