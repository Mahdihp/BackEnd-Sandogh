package com.mahdi.sandogh.utils;


import com.mahdi.sandogh.model.permission.Permission;
import com.mahdi.sandogh.model.permission.PermissionName;
import com.mahdi.sandogh.model.permission.repository.PermissionRepo;
import com.mahdi.sandogh.model.role.Role;
import com.mahdi.sandogh.model.role.RoleName;
import com.mahdi.sandogh.model.role.repository.RoleRepo;
import com.mahdi.sandogh.model.user.User;
import com.mahdi.sandogh.model.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private PermissionRepo permissionRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        addPermission();
        addRoles();
        addUserAdmin();
    }

    private void addPermission() {
        if (permissionRepo.count() <= 0) {
            Permission permission=new Permission();
            permission.setDisplayName(PermissionName.ROLE_ADMIN_ACCESS_ALL);
            permissionRepo.save(permission);
            System.out.println("Insert Permissions...");
        }
    }

    private void addRoles() {
        if (roleRepo.count() <= 0) {
            Role adminRole = new Role();
            adminRole.setName(RoleName.ROLE_ADMIN);
            Permission permission=permissionRepo .findAll().get(0);
            adminRole.setPermissions(Arrays.asList(permission));
            roleRepo.save(adminRole);

            Role adminUser = new Role();
            adminUser.setName(RoleName.ROLE_USER);
            roleRepo.save(adminUser);

            Role adminMember = new Role();
            adminMember.setName(RoleName.ROle_MEMBER);
            roleRepo.save(adminMember);
        }
    }

    private void addUserAdmin() {
        Optional<User> mahdihp = userRepo.findByUserName("mahdihp");
        if (!mahdihp.isPresent()) {
            User user1 = new User();
            user1.setDisplayName("مهدی حسین پور");
            user1.setUserName("mahdihp");
            user1.setNationalId("0386007551");
            user1.setPassword(encoder.encode("123456"));
            user1.setActive(true);

            Role role1 = roleRepo.findByName(RoleName.ROLE_ADMIN).get();

            Set<Role> roles1 = new HashSet<>();
            roles1.add(role1);
            user1.setRoles(roles1);
            userRepo.save(user1);
            System.out.println("Insert");
        }
    }
}
