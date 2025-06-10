package org.saima.LMS.service;

import java.util.List;

import org.saima.LMS.dto.ContactDTO;
import org.saima.LMS.model.Contact;
import org.saima.LMS.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
@Autowired
private ContactRepository contactRepository;

public Contact createContact(ContactDTO contactDto) {
	Contact contact = new Contact(contactDto.getName(),contactDto.getEmail(),contactDto.getMessage());
	
	return contactRepository.save(contact);
}

public List<Contact> getAllMessage() {
		return contactRepository.findAll();
}
	
}
