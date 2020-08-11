package com.mahdi.sandogh.model.permission.repository;

import com.mahdi.sandogh.model.permission.Permission;
import com.mahdi.sandogh.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mahdi
 * DateTime: ۱۱/۰۸/۲۰۲۰:20:44
 * http://github.com/mahdihp
 * http://gitlab.com/mahdihp
 */

@Repository
public interface PermissionRepo extends JpaRepository<Permission,Integer> {
}
