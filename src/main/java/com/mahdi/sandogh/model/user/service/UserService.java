package com.mahdi.sandogh.model.user.service;

import com.mahdi.sandogh.model.role.Role;
import com.mahdi.sandogh.model.role.RoleName;
import com.mahdi.sandogh.model.role.repository.RoleRepo;
import com.mahdi.sandogh.model.user.User;
import com.mahdi.sandogh.model.user.dto.ListUserResponse;
import com.mahdi.sandogh.model.user.dto.LoginForm;
import com.mahdi.sandogh.model.user.dto.UserResponse;
import com.mahdi.sandogh.model.user.dto.UserForm;
import com.mahdi.sandogh.model.user.repository.UserRepo;
import com.mahdi.sandogh.security.jwt.JwtProvider;
import com.mahdi.sandogh.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;


    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;


    public UUID create(UserForm userForm) {
        Boolean exists = userRepo.existsByUserNameAndPassword(userForm.getUserName(), userForm.getPassword());
        UUID uid = UUID.randomUUID();
        if (!exists) {
            User user = new User();
//            user.setUid(uid);
            user.setDisplayName(userForm.getName());
            user.setNationalId(userForm.getNationalId());
            user.setUserName(userForm.getUserName());
            user.setPassword(encoder.encode(userForm.getPassword()));
            user.setActive(userForm.getActive());

            Set<Role> roles = new HashSet<>();
            if (userForm.getUser()) {
                Role clientRole = roleRepo.findByName(RoleName.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(clientRole);
            } else {
                Role clientRole = roleRepo.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(clientRole);
            }
            user.setRoles(roles);
            userRepo.save(user);
            return uid;
        }
        return null;
    }

    public boolean update(UserForm userForm) {
        Optional<User> user = userRepo.findById((userForm.getUserId()));
        if (user.isPresent()) {
            user.get().setNationalId(userForm.getName());
            user.get().setNationalId(userForm.getNationalId());
            user.get().setUserName(userForm.getUserName());
            user.get().setPassword(userForm.getPassword());
            user.get().setActive(userForm.getActive());
            userRepo.save(user.get());
            return false;
        }
        return false;
    }

    public Optional<User> findById(Long userId) {
        Optional<User> user = userRepo.findById((userId));
        return user;
    }

    public Optional<UserResponse> findDTOById(Long userId) {
        Optional<User> user = userRepo.findById((userId));
        if (user.isPresent()) {
            UserResponse userResponse =UserResponse.Builder.anUserResponse()
                    .withUsername(user.get().getUserName())
                    .withNationalId(user.get().getNationalId())
                    .withDisplayName(user.get().getDisplayName())
                    .withLogin(user.get().isLogin())
                    .withActive(user.get().isActive())
                    .withCreateTime(user.get().getCreatedAt())
                    .withUpdateTime(user.get().getUpdatedAt())
                    .build();
            return Optional.ofNullable(userResponse);
        }
        return Optional.empty();
    }

    public Optional<ListUserResponse> findAllDTO() {
        List<User> list = userRepo.findAll();
        if (list != null) {
            ListUserResponse luDTO = new ListUserResponse();
            luDTO.setStatus(HttpStatus.OK.value());
            luDTO.setMessage(AppConstants.KEY_SUCESSE);
            List<UserResponse> dtoList = new ArrayList<>();
            for (User user : list) {
                UserResponse userResponse =UserResponse.Builder.anUserResponse()
                        .withUsername(user.getUserName())
                        .withNationalId(user.getNationalId())
                        .withDisplayName(user.getDisplayName())
                        .withLogin(user.isLogin())
                        .withActive(user.isActive())
                        .withCreateTime(user.getCreatedAt())
                        .withUpdateTime(user.getUpdatedAt())
                        .build();
                dtoList.add(userResponse);
            }
            luDTO.setData(dtoList);
            return Optional.ofNullable(luDTO);
        }
        return Optional.empty();
    }


    public Optional<User> findByUsername(LoginForm loginForm) {
        Optional<User> user = userRepo.findByUserName(loginForm.getUsername());
        return user;
    }
}
