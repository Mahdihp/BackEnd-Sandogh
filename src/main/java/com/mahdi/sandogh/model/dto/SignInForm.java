package com.mahdi.sandogh.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class SignInForm {

    @NotBlank
    @Size(min = 5, max = 30) //
    private String userName;

    @NotBlank
    @Size(min = 5, max = 30)
    private String password;

    public SignInForm() {
    }
}
