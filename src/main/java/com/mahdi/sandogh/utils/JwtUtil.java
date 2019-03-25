package com.mahdi.sandogh.utils;

import com.mahdi.sandogh.model.user.dto.LoginForm;
import com.mahdi.sandogh.model.user.repository.UserRepo;
import com.mahdi.sandogh.security.jwt.JwtProvider;
import com.mahdi.sandogh.security.jwt.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Component
public class JwtUtil {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;


    public JwtResponse generateToken(@Valid @RequestBody LoginForm loginForm) {
        System.out.println("Log---generateToken--------------------:" + loginForm.getUserName());
        System.out.println("Log---generateToken--------------------:" + loginForm.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUserName(),
                        loginForm.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Log---generateToken--------------------:");
        String jwt = jwtProvider.generateJwtToken(authentication);
        System.out.println("Log---generateToken--------------------:");
        return new JwtResponse(jwt);
    }

}
