package nusiss.project.server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_message")
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String contactId;
    private String contactName;
    private String contactEmail;
    private String message;
    private String subject;
    private String createDate;

    // Constructors
    public Contact(String contactId, String contactName, String contactEmail, String message, String subject,
            String createDate) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.message = message;
        this.subject = subject;
        this.createDate = createDate;
    }
    public Contact() {
    }

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
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    // toString()
    @Override
    public String toString() {
        return "Contact [contactId=" + contactId + ", contactName=" + contactName + ", contactEmail=" + contactEmail
                + ", message=" + message + ", subject=" + subject + ", createDate=" + createDate + "]";
    }    
}
