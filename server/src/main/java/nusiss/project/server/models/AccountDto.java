package nusiss.project.server.models;

public class AccountDto {

  private String id;
  private String fullName;
  private String email;
  private String phoneNumber;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  @Override
  public String toString() {
    return "AccountDto [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber
        + "]";
  }


}
