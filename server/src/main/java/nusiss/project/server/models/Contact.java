package nusiss.project.server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_message")
public class Contact {
    
    @Id
    private String contactId;
    private String contactName;
    private String contactEmail;
    private String phoneNumber;
    private String message;
    private String subject;
    private String createdDate;

    // Getters and Setters
    public String getContactId() {
        return contactId;
    }
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    // toString()
    @Override
    public String toString() {
        return "Contact [contactId=" + contactId + ", contactName=" + contactName + ", contactEmail=" + contactEmail
                + ", message=" + message + ", subject=" + subject + ", createdDate=" + createdDate + "]";
    }    
}
