package com.mahdi.sandogh.model.account.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {

    @NotBlank
    @Size(min = 5, max = 30)
    private String firstName;

    @NotBlank
    @Size(min = 5, max = 30)
    private String lastName;

    @NotBlank
    @Size(min = 5, max = 30) //
    private String userName;

    @NotBlank
    @Size(min = 5, max = 30)
    private String password;

    public SignUpForm() {
    }
}
