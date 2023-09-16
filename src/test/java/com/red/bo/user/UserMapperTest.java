package com.red.bo.user;

import com.red.bo.core.contact.Contact;
import com.red.bo.core.user.User;
import com.red.bo.web.contact.mapper.ContactDTO;
import com.red.bo.web.contact.mapper.ContactMapper;
import com.red.bo.web.user.mapper.UserDTO;
import com.red.bo.web.user.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserMapperTest {

        @InjectMocks
        private UserMapper userMapper;

        @Mock
        private ContactMapper contactMapper;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void should_return_a_userDTO_when_call_toUserDTO_method() {
            var contact = new Contact();
            contact.setId(1L);
            contact.setFirstname("contact1");

            User user = new User();
            user.setId(1L);
            user.setUsername("testuser");
            user.setContacts(List.of(contact));

            var contactDTO = ContactDTO.builder().id(1L).firstname("contact1").build();

            when(contactMapper.toContactDTO(contact)).thenReturn(contactDTO);

            UserDTO userDTO = userMapper.toUserDTO(user);

            assertEquals(1L, userDTO.getId());
            assertEquals("testuser", userDTO.getUsername());
            assertEquals(1, userDTO.getContacts().size());
        }
    }
