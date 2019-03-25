package com.mahdi.sandogh.model.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/24/19
 * Time: 12:47
 * https://github.com/mahdihp
 */


@Data
public class LoginForm {

    @NotNull
    private String userName;

    @NotNull
    private String password;

    public LoginForm() {
    }
}
