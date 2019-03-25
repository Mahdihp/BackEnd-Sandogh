package com.mahdi.sandogh.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDTO;
import com.mahdi.sandogh.model.user.User;
import com.mahdi.sandogh.security.jwt.JwtResponse;
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
    private String name;
    private String nationalId;
    private String userName;
    private String password;
    private Boolean active;
    private JwtResponse jwtResponse;

    public UserDTO() {
    }

    public UserDTO(Integer status, String message, String userId) {
        super(status, message);
        this.userId = userId;
    }

    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUid().toString());
        userDTO.setName(user.getDisplayName());
        userDTO.setNationalId(user.getNationalId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setActive(user.isActive());
        return userDTO;
    }
}
