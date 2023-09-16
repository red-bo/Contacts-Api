package com.red.bo.authentication;/*package com.red.bo.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Role  implements GrantedAuthority {
    ADMIN("ADMIN", Arrays.asList(Permission.values())),
    USER("USER", List.of(Permission.ACCESS_DEFAULT_ENDPOINTS));

    public static final String ROLE_PREFIX = "ROLE_";
    private String authority;
    private List<Permission> values;

    @Override
    public String getAuthority() {
        return ROLE_PREFIX + authority;
    }
}

*/
