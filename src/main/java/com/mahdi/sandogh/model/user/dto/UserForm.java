package com.mahdi.sandogh.model.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/20/19
 * Time: 12:56
 * https://github.com/mahdihp
 */

@Data
public class UserForm {

    private Long userId;

    @NotNull
    private String name;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    private String nationalId;

    @NotNull
    private Boolean active;

    @NotNull
    private Boolean user;

    public UserForm() {
    }
}
