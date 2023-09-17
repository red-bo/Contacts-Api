package com.red.bo.core.skills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Skill getByName(String name);

    List<Skill> getSkillsByLevel(String level);
}