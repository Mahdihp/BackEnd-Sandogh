package com.mahdi.sandogh.model.user.controller;

import com.mahdi.sandogh.model.user.dto.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/24/19
 * Time: 12:43
 * https://github.com/mahdihp
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthUserController {

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginForm loginRequest) {

        return null;
    }
}
