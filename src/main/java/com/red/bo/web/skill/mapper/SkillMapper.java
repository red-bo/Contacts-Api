package com.red.bo.web.skill.mapper;

import com.red.bo.core.contact.Contact;
import com.red.bo.core.skills.Skill;
import com.red.bo.core.user.User;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    public SkillDTO toSkillDTO(Skill skill){
        return SkillDTO.builder()
            .id(skill.getId())
            .name(skill.getName())
            .level(skill.getLevel())
            .build();
    }

    public Skill toSkill(RequestedSkill requestedSkill){
        var user = new User();
        user.setId(requestedSkill.getUserId());
        var skill = new Skill();
        skill.setId(requestedSkill.getId());
        skill.setName(requestedSkill.getName());
        skill.setLevel(requestedSkill.getLevel());
        skill.setContacts(requestedSkill.getContacts()
                .stream()
                .map(requestedContact -> {
                    var contact = new Contact();
                    contact.setId(requestedSkill.getId());
                    return contact;})
                .toList());
        skill.setUser(user);
        return skill;
    }
}