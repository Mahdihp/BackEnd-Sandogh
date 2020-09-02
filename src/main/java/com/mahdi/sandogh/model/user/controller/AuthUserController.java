package com.mahdi.sandogh.model.user.controller;

import com.mahdi.sandogh.model.user.User;
import com.mahdi.sandogh.model.user.dto.ListUserResponse;
import com.mahdi.sandogh.model.user.dto.LoginForm;
import com.mahdi.sandogh.model.user.dto.UserResponse;
import com.mahdi.sandogh.model.user.service.UserService;
import com.mahdi.sandogh.security.jwt.JwtResponse;
import com.mahdi.sandogh.utils.AppConstants;
import com.mahdi.sandogh.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/24/19
 * Time: 12:43
 * https://github.com/mahdihp
 */

@RestController
@RequestMapping(AppConstants.KEY_API_USERS)
public class AuthUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginForm loginRequest) {
        Optional<User> user = userService.findByUsername(loginRequest);
        System.out.println(loginRequest.getUsername());
        if (user.isPresent()) {
            JwtResponse jwtResponse = jwtUtil.generateToken(loginRequest);
            if (jwtResponse != null) {
                UserResponse userResponse = UserResponse.Builder.anUserResponse()
                        .withUsername(user.get().getUserName())
                        .withNationalId(user.get().getNationalId())
                        .withDisplayName(user.get().getDisplayName())
                        .withLogin(user.get().isLogin())
                        .withActive(user.get().isActive())
                        .withCreateTime(user.get().getCreatedAt())
                        .withUpdateTime(user.get().getUpdatedAt())
                        .withJwtResponse(jwtResponse)
                        .build();
                ListUserResponse listUserResponse =new ListUserResponse() ;
                listUserResponse.setStatus(HttpStatus.OK.value());
                listUserResponse.setMessage(AppConstants.KEY_SUCESSE);
                listUserResponse.setData(Arrays.asList(userResponse));
                return ResponseEntity.status(HttpStatus.OK).body(listUserResponse);
            }
        }
        ListUserResponse listUserResponse =new ListUserResponse() ;
        listUserResponse.setStatus(201);
        listUserResponse.setMessage(AppConstants.KEY_NOT_FOUND_USER);
        return ResponseEntity.status(HttpStatus.OK).body(listUserResponse);
    }

    @PostMapping(value = "/signout", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> signOut(@Valid @RequestBody LoginForm loginRequest) {
        Optional<User> user = userService.findByUsername(loginRequest);
        System.out.println(loginRequest.getUsername());
        if (user.isPresent()) {
            jwtUtil.generateToken(loginRequest);
            ListUserResponse listUserResponse =new ListUserResponse() ;
            listUserResponse.setStatus(200);
            listUserResponse.setMessage(AppConstants.KEY_SIGNOUT);
            return ResponseEntity.status(HttpStatus.OK).body(listUserResponse);
        }
        ListUserResponse listUserResponse =new ListUserResponse() ;
        listUserResponse.setStatus(201);
        listUserResponse.setMessage(AppConstants.KEY_NOT_FOUND_USER);
        return ResponseEntity.status(HttpStatus.OK).body(listUserResponse);
    }
}
