package com.springblog.springblog.repository;

import com.springblog.springblog.model.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Long> {

    @EntityGraph(attributePaths = { "boards" })
    List<Users> findAll();

    @Query("select u from Users u where u.username like %?1")
    Users findByUsernameQuery(String username);
}
