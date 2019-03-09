package com.mahdi.sandogh.utils;


import com.mahdi.sandogh.model.Role;
import com.mahdi.sandogh.model.RoleName;
import com.mahdi.sandogh.model.User;
import com.mahdi.sandogh.model.sandogh.Account;
import com.mahdi.sandogh.repository.AccountRepo;
import com.mahdi.sandogh.repository.RoleRepo;
import com.mahdi.sandogh.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.ws.soap.Addressing;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    AccountRepo accountRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDatabase();
    }

    private void initDatabase() {
        User user1=new User();
        user1.setUid(UUID.fromString("b1dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        user1.setFirstName("mahdi");
        user1.setLastName("hosseinpour");
        user1.setUserName("mahdihp");
        user1.setPassword("12345");
        user1.setActive(true);

        User user2=new User();
        user2.setUid(UUID.fromString("b2dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        user2.setFirstName("abazar");
        user2.setLastName("jafari");
        user2.setUserName("jafari");
        user2.setPassword("12345");
        user2.setActive(true);

        Role role1 = new Role();
        role1.setName(RoleName.ROLE_ADMIN);

        Role role2 = new Role();
        role2.setName(RoleName.ROLE_USER);

        Set<Role> roles1 = new HashSet<>();
        roles1.add(role1);
        roles1.add(role2);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(role1);
        roles2.add(role2);

        user1.setRoles(roles1);
        user2.setRoles(roles2);

        Account account1=new Account();
        account1.setUid(UUID.fromString("b3dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        account1.setFirstName("mahdi");
        account1.setLastName("hosseinpour");
        account1.setFatherName("ali");
        account1.setAccountNumber(DataUtil.generateNumericRandomAccountNumber(10));



        roleRepo.save(role1);
        roleRepo.save(role2);

        userRepo.save(user1);
        userRepo.save(user2);


    }
}
