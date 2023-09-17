package com.red.bo.web.user.mapper;

import com.red.bo.core.user.User;
import com.red.bo.security.Role;
import com.red.bo.web.contact.mapper.ContactMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final ContactMapper map;

    public UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .contacts(user.getContacts()
                                .stream()
                                .map(map::toContactDTO)
                                .toList())
                .build();
    }

    public User requestedRegisterUsertoUser(RequestedRegisterUser requestedRegisterUser){
        var user = new User();
        user.setUsername(requestedRegisterUser.getUsername());
        user.setPassword(requestedRegisterUser.getPassword());
        user.setRole(Role.USER);
        return user;
    }
}