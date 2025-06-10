package org.saima.LMS.controller;

import java.util.List;

import org.saima.LMS.dto.ContactDTO;
import org.saima.LMS.model.Contact;
import org.saima.LMS.model.Course;
import org.saima.LMS.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;
@PostMapping
public ResponseEntity<Contact>createContact(@Validated @RequestBody ContactDTO contactDto){
	Contact contact = contactService.createContact(contactDto);
	return ResponseEntity.ok(contact);
}

@GetMapping
public ResponseEntity<List<Contact>> getAllMessage() {
    List<Contact> contacts = contactService.getAllMessage();
    return ResponseEntity.ok(contacts);
}

}
