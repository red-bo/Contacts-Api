package com.red.bo.web.user.mapper;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisteredUser {

    @Schema(description = "Username", example = "john")
    private String username;

    @Schema(description = "Password", example = "********")
    private String password;

}
