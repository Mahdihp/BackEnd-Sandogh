package com.mahdi.sandogh.model.user.controller;

import com.mahdi.sandogh.model.BaseDTO;
import com.mahdi.sandogh.model.user.dto.ListUserDTO;
import com.mahdi.sandogh.model.user.dto.UserDTO;
import com.mahdi.sandogh.model.user.dto.UserForm;
import com.mahdi.sandogh.model.user.service.UserService;
import com.mahdi.sandogh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/20/19
 * Time: 13:15
 * https://github.com/mahdihp
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createUser(@Valid @RequestBody UserForm userForm) {
        UUID uid = userService.create(userForm);
        if (uid != null)
            return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_USER, uid.toString()));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_FAIL));
    }

    @PostMapping(value = "/{userid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateUser(@PathVariable(value = "userid") String userId, @Valid @RequestBody UserForm userForm) {
        userForm.setUserId(userId);
        if (userService.update(userForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_USER));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER));
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllUser() {
        Optional<ListUserDTO> list = userService.findAllDTO();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER));
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findUser(@RequestParam("userid") String userId) {
        Optional<UserDTO> user = userService.findDTOById(userId);
        if (user.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER));
    }
}
