package com.mahdi.sandogh.model.user.repository;

import com.mahdi.sandogh.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);

    Optional<User> findByUid(UUID uid);
    Boolean existsByUserName(String username);

    Boolean existsByUserNameAndPassword(String username, String pass);
}
