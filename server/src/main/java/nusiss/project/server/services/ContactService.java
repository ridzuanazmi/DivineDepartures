package nusiss.project.server.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import nusiss.project.server.models.Contact;
import nusiss.project.server.models.ContactDto;
import nusiss.project.server.repositories.ContactRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepo;
    
    public ContactService(ContactRepository contactRepo) {
        this.contactRepo = contactRepo;
    }

    // Generate UUID for the contactId
    public String contactIdGeneration() {
        String contactId = UUID.randomUUID().toString().substring(0, 8);
        return "SR"+contactId;
    }

    // Convert the date into String "dd-MMM-yyyy hh:mm"
    public String dateConvert() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
        String strDate = formatter.format(date);
        System.out.println("Formatted Date: " + strDate);
        return strDate;
    }

    // Save the contact inquiry into the DB
    public Contact saveContact(Contact contact) {
        return this.contactRepo.save(contact);
    }

    public Contact createContact(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setContactId(contactIdGeneration());
        contact.setCreatedDate(dateConvert());
        contact.setContactName(contactDto.getContactName());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setSubject(contactDto.getSubject());
        contact.setMessage(contactDto.getMessage());
        return contact;
    }
}
