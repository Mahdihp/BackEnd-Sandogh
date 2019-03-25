package com.mahdi.sandogh.model.user.service;

import com.mahdi.sandogh.model.user.User;
import com.mahdi.sandogh.model.user.UserPrinciple;
import com.mahdi.sandogh.model.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/24/19
 * Time: 12:36
 * https://github.com/mahdihp
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepo.findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
                );
//         System.out.println("Log---loadUserByUsername--------------------:"+user);
        return UserPrinciple.build(user);
    }
}
