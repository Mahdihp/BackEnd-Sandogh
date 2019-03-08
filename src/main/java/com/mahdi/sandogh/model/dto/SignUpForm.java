package com.mahdi.sandogh.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

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
