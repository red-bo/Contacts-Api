package com.red.bo.core.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(Contact contact) {
        if(contact != null){
            return contactRepository.save(contact);
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact updateContact(Long id, Contact updatedContact) {
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
        return null;
    }

    public boolean deleteContact(Long id) {
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact != null) {
            contactRepository.delete(existingContact);
            return true;
        }
        return false;
    }
}
