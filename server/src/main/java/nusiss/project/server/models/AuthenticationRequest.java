package nusiss.project.server.models;

public class AuthenticationRequest {

    private String email;
    String password;
    
    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public AuthenticationRequest() {
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

    // Builder class
    public static class Builder {
        private String email;
        private String password;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationRequest build() {
            AuthenticationRequest authenticationRequest = new AuthenticationRequest();
            authenticationRequest.email = this.email;
            authenticationRequest.password = this.password;
            return authenticationRequest;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
