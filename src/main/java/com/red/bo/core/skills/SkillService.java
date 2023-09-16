package com.red.bo.core.skills;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    public Skill updateSkill(Long id, Skill updatedSkill) {
        Skill existingSkill = skillRepository.findById(id).orElse(null);
        if (existingSkill != null) {
            existingSkill.setName(updatedSkill.getName());
            existingSkill.setLevel(updatedSkill.getLevel());
            return skillRepository.save(existingSkill);
        }
        return null;
    }

    public boolean deleteSkill(Long id) {
        Skill existingSkill = skillRepository.findById(id).orElse(null);
        if (existingSkill != null) {
            skillRepository.delete(existingSkill);
            return true;
        }
        return false;
    }

    public Skill getSkillByName(String name) {
        if(!name.isBlank()){
            return skillRepository.getByName(name);
        }
        return null;
    }

    public List<Skill> getSkillsByLevel(String level) {
        if(!level.isBlank()){
            return skillRepository.getSkillsByLevel(level.toUpperCase());
        }
        return null;
    }
}
