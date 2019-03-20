package com.mahdi.sandogh.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import lombok.Data;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/20/19
 * Time: 13:08
 * https://github.com/mahdihp
 */


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends BaseDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean active;

    public UserDTO() {
    }
}
