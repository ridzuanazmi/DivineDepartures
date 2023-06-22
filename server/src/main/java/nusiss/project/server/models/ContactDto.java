package nusiss.project.server.models;

public class ContactDto {

  private String contactName;
  private String contactEmail;
  private String phoneNumber;
  private String message;
  private String subject;
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
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
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
  @Override
  public String toString() {
    return "ContactDto [contactName=" + contactName + ", contactEmail=" + contactEmail + ", phoneNumber=" + phoneNumber
        + ", message=" + message + ", subject=" + subject + "]";
  }
  
}
