package com.mahdi.sandogh.model.user.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/24/19
 * Time: 12:47
 * https://github.com/mahdihp
 */


@Data
public class LoginForm {

    @Size(min = 5, max = 30)
    private String userName;

    @Size(min = 5, max = 30)
    private String password;

    public LoginForm() {
    }
}
