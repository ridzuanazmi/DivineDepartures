package nusiss.project.server.models.user;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import nusiss.project.server.models.Shop;

@Entity
@Table(name = "_user")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fullName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Date createdDate;

    @Enumerated(EnumType.STRING) // Tells spring this is enum
    private Role role; // USER or ADMIN value

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Shop> shops;

    // Constructors
    public User() {
    }
    public User(Integer userId, String fullName, String phoneNumber, String email, String password, Date createdDate,
            Role role, List<Shop> shops) {
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.role = role;
        this.shops = shops;
    }
    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    // public String getPassword() {
    //     return password;
    // }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Shop> getShops() {
        return shops;
    }
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", email=" + email
                + ", password=" + password + ", createdDate=" + createdDate + ", role=" + role + ", shops=" + shops
                + "]";
    }
    // UserDetails methods
    // Returns a list of roles 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
        // throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }
    @Override
    public String getUsername() {
        return email; // Username is the email address
        // throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }
    @Override
    public boolean isAccountNonExpired() {
        return true; // set to true if not cannot access
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }
    @Override
    public boolean isAccountNonLocked() {
        return true; // set to true if not cannot access
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // set to true if not cannot access
        // throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }
    @Override
    public boolean isEnabled() {
        return true; // set to true if not cannot access
        // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
    @Override
    public String getPassword() {
        return password;
        // throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    // Builder class
    public static class UserBuilder {

        private Integer userId;
        private String fullName;
        private String phoneNumber;
        private String email;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
        private Date createdDate;
        private Role role;

        UserBuilder() {
        }

        public UserBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }
        public UserBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }
        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }
        public UserBuilder createdDate(Date createdDate) {
            this.createdDate=createdDate;
            return this;
        }

        public User build() {
            User user = new User();
            user.userId = this.userId;
            user.fullName = this.fullName;
            user.phoneNumber = this.phoneNumber;
            user.email = this.email;
            user.password = this.password;
            user.createdDate = this.createdDate;
            user.role = this.role;
            return user;
        }
    }
    public static UserBuilder builder() {
        return new UserBuilder();
    }
    
}
