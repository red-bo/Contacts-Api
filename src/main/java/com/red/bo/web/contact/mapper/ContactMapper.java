package com.red.bo.web.contact.mapper;

import com.red.bo.core.contact.Contact;
import com.red.bo.core.user.User;
import com.red.bo.web.skill.mapper.SkillMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContactMapper {

    private final SkillMapper map;

    public ContactDTO toContactDTO(Contact contact){

        return ContactDTO.builder()
                .id(contact.getId())
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .fullname(contact.getFullname())
                .email(contact.getEmail())
                .mobilePhoneNumber(contact.getPhoneNumber())
                .address(contact.getAddress())
                .skills(contact.getSkills().stream()
                        .map(map::toSkillDTO)
                        .toList())
                .build();
    }

    public Contact toContact(RequestedContact requestedContact){
        var user = new User();
        user.setId(requestedContact.getUser().getId());

        var contact = new Contact();
        contact.setId(requestedContact.getId());
        contact.setFirstname(requestedContact.getFirstname());
        contact.setLastname(requestedContact.getLastname());
        contact.setFullname(requestedContact.getFirstname() + requestedContact.getLastname());
        contact.setEmail(requestedContact.getEmail());
        contact.setPhoneNumber(requestedContact.getMobilePhoneNumber());
        contact.setAddress(requestedContact.getAddress());
        contact.setSkills(requestedContact.getSkills()
                .stream()
                .map(map::toSkill)
                .toList());
        contact.setUser(user);
        return contact;
    }
}