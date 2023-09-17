package com.red.bo.contact;

import com.red.bo.core.contact.Contact;
import com.red.bo.core.contact.ContactService;
import com.red.bo.web.contact.ContactController;
import com.red.bo.web.contact.mapper.ContactDTO;
import com.red.bo.web.contact.mapper.ContactMapper;
import com.red.bo.web.contact.mapper.RequestedContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class ContactControllerTest {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactService contactService;
    @Mock
    private ContactMapper contactMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateContact() {
        var requestedContact = RequestedContact.builder()
                .id(1L)
                .build();

        Contact contact = new Contact();
        contact.setId(1L);

        when(contactMapper.toContact(requestedContact)).thenReturn(contact);
        when(contactService.createContact(contact)).thenReturn(contact);
        when(contactMapper.toContactDTO(contact)).thenReturn(ContactDTO.builder().build());

        ResponseEntity<ContactDTO> responseEntity = contactController.createContact(requestedContact);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        contacts.add(new Contact());

        when(contactService.getAllContacts()).thenReturn(contacts);
        when(contactMapper.toContactDTO(any())).thenReturn(ContactDTO.builder().build());

        ResponseEntity<List<ContactDTO>> responseEntity = contactController.getAllContacts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetContactById() {
        Contact contact = new Contact();
        contact.setId(1L);

        when(contactService.getContactById(1L)).thenReturn(contact);
        when(contactService.getContactById(2L)).thenReturn(null);
        when(contactMapper.toContactDTO(contact)).thenReturn(ContactDTO.builder().build());

        ResponseEntity<ContactDTO> responseEntity1 = contactController.getContactById(1L);
        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        ResponseEntity<ContactDTO> responseEntity2 = contactController.getContactById(2L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
    }

    @Test
    public void testUpdateContact() {
        Contact updatedContact = new Contact();
        updatedContact.setId(1L);

        when(contactService.updateContact(1L, updatedContact)).thenReturn(updatedContact);
        when(contactService.updateContact(2L, updatedContact)).thenReturn(null);
        when(contactMapper.toContactDTO(updatedContact)).thenReturn(ContactDTO.builder().build());

        ResponseEntity<ContactDTO> responseEntity1 = contactController.updateContact(1L, updatedContact);

        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());

        ResponseEntity<ContactDTO> responseEntity2 = contactController.updateContact(2L, updatedContact);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
    }

    @Test
    public void testDeleteContact() {
        when(contactService.deleteContact(1L)).thenReturn(true);
        when(contactService.deleteContact(2L)).thenReturn(false);

        ResponseEntity<Void> responseEntity1 = contactController.deleteContact(1L);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity1.getStatusCode());

        ResponseEntity<Void> responseEntity2 = contactController.deleteContact(2L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
    }
}
