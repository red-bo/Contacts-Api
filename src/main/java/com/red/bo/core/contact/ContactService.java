package com.red.bo.core.contact;

import com.red.bo.core.exception.ContactNotFoundException;
import com.red.bo.core.exception.DeleteContactException;
import com.red.bo.core.exception.UpdateContactException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(Contact contact) {
        log.debug("try to create contact : ", contact);
        if(contact != null){
            return contactRepository.save(contact);
        }
        throw new RuntimeException("Cannot save a null contact");
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        log.debug("Try to found contact by id : " + id);
        return contactRepository.findById(id).orElseThrow(() -> {
            throw new ContactNotFoundException("Contact not found id : " + id);
        });
    }

    public Contact updateContact(Long id, Contact updatedContact) {
        log.debug("Try to update contact by id : " + id);
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact != null) {
            existingContact.setFirstname(updatedContact.getFirstname());
            existingContact.setLastname(updatedContact.getLastname());
            existingContact.setFullname(updatedContact.getFullname());
            existingContact.setAddress(updatedContact.getAddress());
            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setPhoneNumber(updatedContact.getPhoneNumber());

            return contactRepository.save(existingContact);
        }
        throw new UpdateContactException("Contact could not be updated");
    }

    public boolean deleteContact(Long id) {
        log.debug("Try to delete contact by id : " + id);
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact != null) {
            contactRepository.delete(existingContact);
            return true;
        }
        throw new DeleteContactException("Contact could not be deleted");
    }
}
