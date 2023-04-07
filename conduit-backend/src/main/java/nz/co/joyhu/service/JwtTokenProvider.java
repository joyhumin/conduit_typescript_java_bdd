package nz.co.joyhu.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final Long JWT_TOKEN_VALIDITY = ChronoUnit.HOURS.getDuration().getSeconds();

    /*
{
"email": "testitoutstring@test.com",
"username": "testitout",
"iat": 1663055087,
"exp": 1668239087
}
timeout in one hour
 */
    public String generateToken(String username, String email) {
        Map<String, Object> claims = Map.of(
                "username", username,
                "email", email
        );

        return doGenerateToken(claims);
    }

    private String doGenerateToken(Map<String, Object> claims) {
        log.info("Starting generate jwt token");
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key)
                .compact();
    }
}
