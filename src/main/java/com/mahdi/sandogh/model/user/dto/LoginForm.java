package com.mahdi.sandogh.model.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/24/19
 * Time: 12:47
 * https://github.com/mahdihp
 */


@Setter
@Getter
public class LoginForm {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public LoginForm() {
    }
}
