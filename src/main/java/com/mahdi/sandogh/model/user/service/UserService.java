package com.mahdi.sandogh.model.user.service;

import com.mahdi.sandogh.model.role.Role;
import com.mahdi.sandogh.model.role.RoleName;
import com.mahdi.sandogh.model.role.repository.RoleRepo;
import com.mahdi.sandogh.model.user.User;
import com.mahdi.sandogh.model.user.dto.ListUserDTO;
import com.mahdi.sandogh.model.user.dto.UserDTO;
import com.mahdi.sandogh.model.user.dto.UserForm;
import com.mahdi.sandogh.model.user.repository.UserRepo;
import com.mahdi.sandogh.security.jwt.JwtProvider;
import com.mahdi.sandogh.utils.Constants;
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
            user.setUid(uid);
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
        Optional<User> user = userRepo.findByUid(UUID.fromString(userForm.getUserId()));
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

    public Optional<User> findById(String userId) {
        Optional<User> user = userRepo.findByUid(UUID.fromString(userId));
        return user;
    }

    public Optional<UserDTO> findDTOById(String userId) {
        Optional<User> user = userRepo.findByUid(UUID.fromString(userId));
        if (user.isPresent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setStatus(HttpStatus.OK.value());
            userDTO.setMessage(Constants.KEY_SUCESSE);

            userDTO.setUserId(user.get().getUid().toString());

            userDTO.setName(user.get().getDisplayName());
            userDTO.setNationalId(user.get().getNationalId());
            userDTO.setUserName(user.get().getUserName());
            userDTO.setPassword(user.get().getPassword());
            userDTO.setActive(user.get().isActive());
            return Optional.ofNullable(userDTO);
        }
        return Optional.empty();
    }

    public Optional<ListUserDTO> findAllDTO() {
        List<User> list = userRepo.findAll();
        if (list != null) {
            ListUserDTO luDTO = new ListUserDTO();
            luDTO.setStatus(HttpStatus.OK.value());
            luDTO.setMessage(Constants.KEY_SUCESSE);
            List<UserDTO> dtoList = new ArrayList<>();
            for (User user : list) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(user.getUid().toString());
                userDTO.setNationalId(user.getNationalId());
                userDTO.setName(user.getDisplayName());
                userDTO.setUserName(user.getUserName());
                userDTO.setPassword(user.getPassword());
                userDTO.setActive(user.isActive());
                dtoList.add(userDTO);
            }
            luDTO.setData(dtoList);
            return Optional.ofNullable(luDTO);
        }
        return Optional.empty();
    }


    public Optional<User> findByUsername(String userName) {
        Optional<User> user = userRepo.findByUserName(userName);
        return user;
    }
}
