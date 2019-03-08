package com.mahdi.sandogh.repository;

import com.mahdi.sandogh.model.Role;
import com.mahdi.sandogh.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleName roleName);

}
