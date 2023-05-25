package nusiss.project.server.models;

public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse() {
    }
    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    // Builder class
    public static class Builder {
        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.token = this.token;
            return authenticationResponse;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
