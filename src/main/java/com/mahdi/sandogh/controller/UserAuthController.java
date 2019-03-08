package com.mahdi.sandogh.controller;


import com.mahdi.sandogh.model.dto.SignInForm;
import com.mahdi.sandogh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users/auth")
public class UserAuthController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInForm signInForm) {
        return null;
    }


}
