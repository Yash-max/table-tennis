package com.yash.tabletennis.dao;

import com.yash.tabletennis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String email);

    boolean existsByUsername(String email);
}
