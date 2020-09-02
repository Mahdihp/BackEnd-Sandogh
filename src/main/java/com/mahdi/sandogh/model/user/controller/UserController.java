package com.mahdi.sandogh.model.user.controller;

import com.mahdi.sandogh.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /*@PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUser(@Valid @RequestBody UserForm userForm) {
        UUID uid = userService.create(userForm);
        if (uid != null)
            return ResponseEntity.status(HttpStatus.OK).body(new UserDto(HttpStatus.OK.value(), AppConstants.KEY_CREATE_USER, uid.toString()));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_FAIL).createBaseDto());
    }

    @PostMapping(value = "/{userid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUser(@PathVariable(value = "userid") String userId, @Valid @RequestBody UserForm userForm) {
        userForm.setUserId(Long.valueOf(userId));
        if (userService.update(userForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_UPDATE_USER).createBaseDto());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_USER).createBaseDto());
    }

    @PostMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllUser() {
        Optional<ListUserDto> list = userService.findAllDTO();
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_USER).createBaseDto());
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findUser(@RequestParam("userid") String userId) {
        Optional<UserDto> user = userService.findDTOById(Long.valueOf(userId));
        if (user.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDtoBuilder().setStatus(HttpStatus.OK.value()).setMessage(AppConstants.KEY_NOT_FOUND_USER).createBaseDto());
    }*/
}
