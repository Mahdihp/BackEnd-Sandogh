package com.mahdi.sandogh.model.user.repository;

import com.mahdi.sandogh.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);
    Boolean existsByUserName(String username);
}
