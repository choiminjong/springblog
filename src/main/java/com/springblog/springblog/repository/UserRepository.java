package com.springblog.springblog.repository;

import com.springblog.springblog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {

}
