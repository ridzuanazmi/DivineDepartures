package nusiss.project.server.models;

import nusiss.project.server.models.user.Role;

public class RegisterRequest {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
    
    public RegisterRequest(String fullName, String phoneNumber, String email, String password, Role role) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public RegisterRequest() {
    }
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegisterRequest [fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password="
                + password + ", role=" + role + "]";
    }

    
}
