package com.red.bo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Role  implements GrantedAuthority {
    ADMIN("ADMIN", Arrays.asList(Permission.values())),
    USER("USER", java.util.List.of(Permission.ACCESS_DEFAULT_ENDPOINTS));

    public static final String ROLE_PREFIX = "ROLE_";
    private String authority;
    private List<Permission> permissions;

    @Override
    public String getAuthority() {
        return ROLE_PREFIX + authority;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
