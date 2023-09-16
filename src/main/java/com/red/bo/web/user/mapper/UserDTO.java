package com.red.bo.web.user.mapper;

import com.red.bo.web.contact.mapper.ContactDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class UserDTO {
    @Schema(description = "ID of the user")
    private Long id;

    @Schema(description = "Username", example = "john")
    private String username;

    @Schema(description = "Contact's user")
    private List<ContactDTO> contacts;
}
