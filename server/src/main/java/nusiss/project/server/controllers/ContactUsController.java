package nusiss.project.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.project.server.models.Contact;
import nusiss.project.server.services.ContactService;

@RestController
@RequestMapping(path = "/contact")
public class ContactUsController {

    private final ContactService contactSrvc;

    public ContactUsController(ContactService contactSrvc) {
        this.contactSrvc = contactSrvc;
    }

    @PostMapping
    public ResponseEntity<?> saveContactInquiryDetails(@RequestBody Contact contact) {

        try {
            contact.setContactId(contactSrvc.contactIdGeneration());
            contact.setCreateDate(contactSrvc.dateConvert());
            Contact savedContact = this.contactSrvc.saveContact(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
        } catch (Exception e) {
            // Log the error message for debugging and return a response with an error
            // message.
            // The error message should not reveal sensitive details about your
            // implementation.
            System.out.println("Error occurred while saving the contact: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while saving the contact. Please try again later.");
        }
    }
}
