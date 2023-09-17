package com.red.bo.web.skill;

import com.red.bo.core.skills.SkillService;
import com.red.bo.core.user.User;
import com.red.bo.web.skill.mapper.RequestedSkill;
import com.red.bo.web.skill.mapper.SkillDTO;
import com.red.bo.web.skill.mapper.SkillMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@AllArgsConstructor
@Tag(name = "Skill Management System")
public class SkillController {

    private final SkillService skillService;
    private final SkillMapper map;

    @Operation(description = "Create a new skill")
    @PostMapping
    public ResponseEntity<SkillDTO> createSkill(@Valid @RequestBody RequestedSkill skill, @AuthenticationPrincipal User user) {
        skill.setUserId(user.getId());
        SkillDTO createdSkill = map.toSkillDTO(skillService.createSkill(map.toSkill(skill)));
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @Operation(description = "Get all skills")
    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills(@AuthenticationPrincipal User user) {
        List<SkillDTO> skills = skillService.getAllSkills()
                .stream()
                .filter(skill -> skill.getUser().getId().equals(user.getId()))
                .map(map::toSkillDTO)
                .toList();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @Operation(description = "Get skill by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@NotNull @PathVariable Long id, @AuthenticationPrincipal User user) {
        var skill = skillService.getSkillById(id);
        if (skill != null && skill.getUser().getId().equals(user.getId())) {
            return new ResponseEntity<>(map.toSkillDTO(skill), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Update a skill by ID")
    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@NotNull @PathVariable Long id,
                                                @Valid @RequestBody RequestedSkill updatedSkill,
                                                @AuthenticationPrincipal User user) {
        var skill = skillService.getSkillById(id);
        if (skill != null && skill.getUser().getId().equals(user.getId())) {
            var skillDTO = map.toSkillDTO(skillService.updateSkill(id, map.toSkill(updatedSkill)));
            return new ResponseEntity<>(skillDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Delete a skill by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@NotNull @PathVariable Long id, @AuthenticationPrincipal User user) {
        var skill = skillService.getSkillById(id);
        if(skill != null && skill.getUser().getId().equals(user.getId())){
            boolean deleted = skillService.deleteSkill(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}