package com.red.bo.security;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
public class AuthenticationUser {

    private String username;
    private String password;
    private Role role;
    private Collection<? extends GrantedAuthority> permissions;

}