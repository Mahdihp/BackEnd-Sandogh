package com.mahdi.sandogh.utils;


import com.mahdi.sandogh.model.account.repository.AccountRepo;
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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
      //  initDatabase();

        System.out.println(accountRepo.count());
    }

    private void initDatabase() {
        User user1=new User();
        user1.setUid(UUID.fromString("13981528-1d44-4ae3-9dc0-c3b8213d45a6"));
        user1.setDisplayName("hosseinpour");
        user1.setUserName("mahdihp");
        user1.setNationalId("0386007551");
        user1.setPassword(encoder.encode("123456"));
        user1.setActive(true);

//        User user2=new User();
//        user2.setUid(UUID.fromString("13982528-1d44-4ae3-9dc0-c3b8213d45a6"));
//        user2.setDisplayName("abazar");
//        user2.setUserName("jafari");
//        user2.setNationalId("0386007551");
//        user2.setPassword(encoder.encode("123456"));
//        user2.setActive(true);

        Role role1 = new Role();
        role1.setName(RoleName.ROLE_USER);


        Set<Role> roles1 = new HashSet<>();
        roles1.add(role1);

        System.out.println("Log---initDatabase--------------------:" + user1.getPassword());
        user1.setRoles(roles1);
//        user2.setRoles(roles1);

        roleRepo.save(role1);

        userRepo.save(user1);
//        userRepo.save(user2);

//        accountRepo.save(account1);


    }
}
