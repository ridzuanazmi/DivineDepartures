package nusiss.project.server.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
Signing key in JWT is a secret 'key' that is used to 
digitally sign the JWT to create the signature part
and is used to verify that the sender of the JWT is
who it claims to be and ensure the message wasn't changed
along the way. So to ensure the same person or same client
is sending this JWT key is the one who he claims to be
signing key from https://www.allkeysgenerator.com/
*/

/* 
 * JwtService class to:
 * 1. Extract claims (payload) from JWT
 * 2. Extract username from JWT
 * 3. Check if the JWT is valid (not expired)
 * 4. Generate JWT with and w/o extra claims (payload) with a 256-bit signing key 
 * 5. TODO: Extracting roles claim in JWT
 * 6. TODO:  Blacklisting users JWT if they logout 
 */
@Service
public class JwtService {

    private static final String SECRET_KEY = "26452948404D635166546A576E5A7134743777217A25432A462D4A614E645267";
    private static final int KEY_EXPIRATION = 1000 * 60 * 60 * 24; // JWT expires in 1 day

    // Get username from token
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject); // subject should be the email or username of the user
    }

    // Get all claims or payload from a JWt
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); 
        return claimsResolver.apply(claims);
    }

    // Create JWT with no extra claims(payload) with security.core.UserDetails
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Create a JWT with extra claims(payload)
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {

        return Jwts
                .builder()
                .setClaims(extraClaims) // Sets JWT payload to be JSON claims instance specified name/value pairs
                .setSubject(userDetails.getUsername()) // Sets claims subject value which is the username of the user
                .setIssuedAt(new Date(System.currentTimeMillis())) // Sets JWT creation timestamp and to check expiration time if it is still valid
                .setExpiration(new Date(System.currentTimeMillis() + KEY_EXPIRATION)) // Sets how long this token should be valid
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Sets the SECRET_KEY
                .compact(); // generate JWT
    }

    // Takes in the JWT and user details to check if it is valid
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Helper methods
    // Extract all claims from a single JWT
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Verify signature requires a secret 256-bit key
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // decode secret key
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
