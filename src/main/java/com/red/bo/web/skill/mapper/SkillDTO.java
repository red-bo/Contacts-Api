package com.red.bo.web.skill.mapper;

import com.red.bo.core.skills.SkillLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillDTO {
    @Schema(description = "ID of the skill")
    private Long id;

    @Schema(description = "Name of the skill", example = "EXPERT")
    private String name;

    @Schema(description = "Level of the skill", example = "EXPERT")
    private SkillLevel level;
}
