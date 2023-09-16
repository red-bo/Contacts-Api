package com.red.bo.web.contact;

import com.red.bo.core.contact.Contact;
import com.red.bo.core.contact.ContactService;
import com.red.bo.web.contact.mapper.ContactDTO;
import com.red.bo.web.contact.mapper.ContactMapper;
import com.red.bo.web.contact.mapper.RequestedContact;
import com.red.bo.web.user.mapper.RequestedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@AllArgsConstructor
@Tag(name = "Contact Management System")
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper map;

    @Operation(description = "Create a new contact")
    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody RequestedContact contact) {
        contact.setUser(RequestedUser.builder().id((long)1).build());
        Contact createdContact = contactService.createContact(map.toContact(contact));
        return new ResponseEntity<>(map.toContactDTO(createdContact), HttpStatus.CREATED);
    }

    @Operation(description = "Get all contacts")
    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.getAllContacts()
                .stream()
                .map(map::toContactDTO)
                .toList();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @Operation(description = "Get contact by ID")
    @ApiResponse(responseCode = "200", description = "Contact found")
    @ApiResponse(responseCode = "400", description = "Contact not found")
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@NotNull @PathVariable Long id) {
        ContactDTO contact = map.toContactDTO(contactService.getContactById(id));
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Update a contact by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@NotNull @PathVariable Long id, @Valid @RequestBody Contact updatedContact) {
        ContactDTO contact = map.toContactDTO(contactService.updateContact(id, updatedContact));
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Delete a contact by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@NotNull @PathVariable Long id) {
        boolean deleted = contactService.deleteContact(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
