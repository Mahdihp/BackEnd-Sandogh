package com.mahdi.sandogh.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mahdi.sandogh.model.BaseDto;
import com.mahdi.sandogh.model.permission.Permission;
import com.mahdi.sandogh.model.role.Role;
import com.mahdi.sandogh.model.role.RoleName;
import com.mahdi.sandogh.model.user.User;
import com.mahdi.sandogh.security.jwt.JwtResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/20/19
 * Time: 13:08
 * https://github.com/mahdihp
 */


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String name;
    private String nationalId;
    private String username;
    private String displayName;
    private Boolean login;
    private Boolean active;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private JwtResponse jwtResponse;
    private Role roles;
    private List<Permission> permissions;


    public static final class Builder {


        private UserResponse userResponse;

        private Builder() {
            userResponse = new UserResponse();
        }

        public static Builder anUserResponse() {
            return new Builder();
        }

        public Builder withName(String name) {
            userResponse.setName(name);
            return this;
        }

        public Builder withNationalId(String nationalId) {
            userResponse.setNationalId(nationalId);
            return this;
        }

        public Builder withUsername(String username) {
            userResponse.setUsername(username);
            return this;
        }

        public Builder withDisplayName(String displayName) {
            userResponse.setDisplayName(displayName);
            return this;
        }

        public Builder withLogin(Boolean login) {
            userResponse.setLogin(login);
            return this;
        }

        public Builder withActive(Boolean active) {
            userResponse.setActive(active);
            return this;
        }

        public Builder withCreateTime(LocalDateTime createTime) {
            userResponse.setCreateTime(createTime);
            return this;
        }

        public Builder withUpdateTime(LocalDateTime updateTime) {
            userResponse.setUpdateTime(updateTime);
            return this;
        }

        public Builder withJwtResponse(JwtResponse jwtResponse) {
            userResponse.setJwtResponse(jwtResponse);
            return this;
        }

        public Builder withRoles(Role roles) {
            userResponse.setRoles(roles);
            return this;
        }

        public Builder withPermissions(List<Permission> permissions) {
            userResponse.setPermissions(permissions);
            return this;
        }

        public Builder but() {
            return anUserResponse().withName(userResponse.getName()).withNationalId(userResponse.getNationalId()).withUsername(userResponse.getUsername()).withDisplayName(userResponse.getDisplayName()).withLogin(userResponse.getLogin()).withActive(userResponse.getActive()).withCreateTime(userResponse.getCreateTime()).withUpdateTime(userResponse.getUpdateTime()).withJwtResponse(userResponse.getJwtResponse()).withRoles(userResponse.getRoles()).withPermissions(userResponse.getPermissions());
        }

        public UserResponse build() {
            return userResponse;
        }
    }
}