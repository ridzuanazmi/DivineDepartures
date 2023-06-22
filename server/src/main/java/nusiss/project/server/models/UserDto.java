package nusiss.project.server.models;

public class UserDto {
  
  private String fullName;
  private String email;
  private String phoneNumber;
  private String role;
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  @Override
  public String toString() {
    return "UserDto [fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", role=" + role
        + "]";
  }

  
}
