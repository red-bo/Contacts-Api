package com.red.bo.web.contact.mapper;

import com.red.bo.web.skill.mapper.SkillDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContactDTO {
    private Long id;

    @Schema(description = "Firstname", example = "John")
    private String firstname;

    @Schema(description = "Lastname", example = "Doe")
    private String lastname;

    private String fullname;

    @Schema(description = "Adress", example = "152 rue Jean Moulin, Douvaine 74140")
    private String address;

    @Schema(description = "Email", example = "john.doe@email.com")
    private String email;

    @Schema(description = "Phone number", example = "0102030405")
    private String mobilePhoneNumber;

    @Schema(description = "Contact's skills", example = "Java, Expert")
    private List<SkillDTO> skills;
}
