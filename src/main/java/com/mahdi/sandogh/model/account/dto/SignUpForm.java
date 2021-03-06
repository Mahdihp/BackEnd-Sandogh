package com.mahdi.sandogh.model.account.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {

    @NotNull
    @Size(min = 5, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 5, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 5, max = 30) //
    private String userName;

    @NotNull
    @Size(min = 5, max = 30)
    private String password;

    public SignUpForm() {
    }
}
