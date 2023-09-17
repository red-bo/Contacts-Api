package com.red.bo.web.skill.mapper;

import com.red.bo.core.skills.SkillLevel;
import com.red.bo.web.contact.mapper.RequestedContact;
import com.red.bo.web.skills.validation.ValidSkill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@ValidSkill
public class RequestedSkill {
    @Schema(description = "ID of the skill")
    private Long id;

    @Schema(description = "Name of the skill", example = "EXPERT")
    private String name;

    @Schema(description = "Level of the skill", example = "EXPERT")
    private SkillLevel level;

    @Schema(description = "Contact list which has the skill")
    private List<RequestedContact> contacts;
}

