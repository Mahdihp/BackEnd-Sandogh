package com.mahdi.sandogh.model.user.controller;

import com.mahdi.sandogh.model.BaseDTO;
import com.mahdi.sandogh.model.user.User;
import com.mahdi.sandogh.model.user.dto.LoginForm;
import com.mahdi.sandogh.model.user.dto.UserDTO;
import com.mahdi.sandogh.model.user.service.UserService;
import com.mahdi.sandogh.security.jwt.JwtResponse;
import com.mahdi.sandogh.utils.Constants;
import com.mahdi.sandogh.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginForm loginRequest) {
        Optional<User> user = userService.findByUsername(loginRequest.getUserName());
        if (user.isPresent()) {
            JwtResponse jwtResponse = jwtUtil.generateToken(loginRequest);
            if (jwtResponse != null) {
                UserDTO userDTO = UserDTO.convertToUserDTO(user.get());
                userDTO.setStatus(HttpStatus.OK.value());
                userDTO.setMessage(Constants.KEY_SUCESSE);
                userDTO.setJwtResponse(jwtResponse);
                return ResponseEntity.status(HttpStatus.OK).body(userDTO);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER));
    }
}
