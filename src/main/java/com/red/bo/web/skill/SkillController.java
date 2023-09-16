package com.red.bo.web.skill;

import com.red.bo.core.skills.SkillService;
import com.red.bo.web.skill.mapper.RequestedSkill;
import com.red.bo.web.skill.mapper.SkillDTO;
import com.red.bo.web.skill.mapper.SkillMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@AllArgsConstructor
@Tag(name = "Skill Management System")
public class SkillController {

    private final SkillService skillService;
    private final SkillMapper skillMapper;

    @Operation(description = "Create a new skill")
    @PostMapping
    public ResponseEntity<SkillDTO> createSkill(@Valid @RequestBody RequestedSkill skill) {
        SkillDTO createdSkill = skillMapper.toSkillDTO(skillService.createSkill(skillMapper.toSkill(skill)));
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @Operation(description = "Get all skills")
    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skills = skillService.getAllSkills()
                .stream()
                .map(skillMapper::toSkillDTO)
                .toList();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @Operation(description = "Get skill by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@NotNull @PathVariable Long id) {
        var skil = skillService.getSkillById(id);
        SkillDTO skill = skillMapper.toSkillDTO(skil);
        if (skill != null) {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Update a skill by ID")
    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@NotNull @PathVariable Long id,
                                                      @Valid @RequestBody RequestedSkill updatedSkill) {
        var skill = skillMapper
                .toSkillDTO(skillService.updateSkill(id, skillMapper.toSkill(updatedSkill)));
        if (skill != null) {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Delete a skill by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@NotNull @PathVariable Long id) {
        boolean deleted = skillService.deleteSkill(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Delete all skills by level")
    @GetMapping("/level")
    public ResponseEntity<List<SkillDTO>> getSkillsByLevel(
            @RequestParam @NotBlank(message = "Level cannot be blank")
            @Pattern(regexp = "^(junior|intermediate|senior|expert)$", message = "Invalid level") String level) {
        List<SkillDTO> skills = skillService.getSkillsByLevel(level)
                .stream()
                .map(skillMapper::toSkillDTO)
                .toList();
        if (!skills.isEmpty()) {
            return new ResponseEntity<>(skills, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Delete all skills by name")
    @GetMapping("/name")
    public ResponseEntity<SkillDTO> getSkillByName(
            @RequestParam(name = "name") @NotBlank(message = "Name cannot be blank") String name) {
        SkillDTO skill = skillMapper.toSkillDTO(skillService.getSkillByName(name));
        if (skill != null) {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}