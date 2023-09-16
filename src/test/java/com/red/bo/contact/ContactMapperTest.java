package com.red.bo.contact;

import com.red.bo.core.contact.Contact;
import com.red.bo.core.skills.Skill;
import com.red.bo.web.contact.mapper.ContactDTO;
import com.red.bo.web.contact.mapper.ContactMapper;
import com.red.bo.web.contact.mapper.RequestedContact;
import com.red.bo.web.skill.mapper.RequestedSkill;
import com.red.bo.web.skill.mapper.SkillDTO;
import com.red.bo.web.skill.mapper.SkillMapper;
import com.red.bo.web.user.mapper.RequestedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ContactMapperTest {

    private ContactMapper contactMapper;

    @Mock
    private SkillMapper skillMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactMapper = new ContactMapper(skillMapper);
    }

    @Test
    public void testToContactDTO() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstname("John");
        contact.setLastname("Doe");
        contact.setEmail("john.doe@example.com");

        var skill1 = new Skill();
        skill1.setId(1L);
        skill1.setName("Java");

        var skill2 = new Skill();
        skill2.setId(2L);
        skill2.setName("Spring");

        var skills = List.of(skill1, skill2);

        contact.setSkills(skills);

        var skillDTO1 = SkillDTO.builder().id(1L).name("Java").build();
        var skillDTO2 = SkillDTO.builder().id(2L).name("Spring").build();
        when(skillMapper.toSkillDTO(skill1)).thenReturn(skillDTO1);
        when(skillMapper.toSkillDTO(skill2)).thenReturn(skillDTO2);

        ContactDTO contactDTO = contactMapper.toContactDTO(contact);

        assertEquals(1L, contactDTO.getId());
        assertEquals("John", contactDTO.getFirstname());
        assertEquals("Doe", contactDTO.getLastname());
        assertEquals("john.doe@example.com", contactDTO.getEmail());
        assertEquals(2, contactDTO.getSkills().size());
        assertEquals(1L, contactDTO.getSkills().get(0).getId());
        assertEquals("Java", contactDTO.getSkills().get(0).getName());
        assertEquals(2L, contactDTO.getSkills().get(1).getId());
        assertEquals("Spring", contactDTO.getSkills().get(1).getName());
    }

    @Test
    public void testToContact() {
        var requestedContact = RequestedContact.builder()
                .id(1L)
                .firstname("Jane")
                .lastname("Smith")
                .email("jane.smith@example.com")
                .user(RequestedUser.builder().build())
                .build();

        var rSkill1 = RequestedSkill.builder().id(1L).name("Java").build();
        var rSkill2 = RequestedSkill.builder().id(2L).name("Spring").build();

        var rSkills = List.of(rSkill1, rSkill2);

        requestedContact.setSkills(rSkills);

        var skill1 = new Skill();
        skill1.setId(1L);
        skill1.setName("Java");

        var skill2 = new Skill();
        skill2.setId(2L);
        skill2.setName("Spring");

        var skills = List.of(skill1, skill2);

        when(skillMapper.toSkill(rSkill1)).thenReturn(skill1);
        when(skillMapper.toSkill(rSkill2)).thenReturn(skill2);

        Contact contact = contactMapper.toContact(requestedContact);

        assertEquals(1L, contact.getId());
        assertEquals("Jane", contact.getFirstname());
        assertEquals("Smith", contact.getLastname());
        assertEquals("jane.smith@example.com", contact.getEmail());
        assertEquals(2, contact.getSkills().size());
        assertEquals(1L, contact.getSkills().get(0).getId());
        assertEquals("Java", contact.getSkills().get(0).getName());
        assertEquals(2L, contact.getSkills().get(1).getId());
        assertEquals("Spring", contact.getSkills().get(1).getName());
    }
}
