package com.mahdi.sandogh.model.user.dto;

import lombok.Data;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/20/19
 * Time: 12:56
 * https://github.com/mahdihp
 */

@Data
public class UserForm {

    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean active;
    private Boolean user;

    public UserForm() {
    }
}
