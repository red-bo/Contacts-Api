package com.red.bo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Permission implements GrantedAuthority {
    ACCESS_DEFAULT_ENDPOINTS("ACCESS_DEFAULT_ENDPOINTS");
    private final String authority;

    @Override
    public String toString() {
        return getAuthority();
    }
}