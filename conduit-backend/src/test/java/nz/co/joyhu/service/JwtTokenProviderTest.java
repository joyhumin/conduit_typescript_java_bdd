package nz.co.joyhu.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.ScopedMock;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class JwtTokenProviderTest {

    private JwtTokenProvider tokenProvider;
    private List<MockedStatic<?>> statics;

    @Before
    public void setUp() {
        tokenProvider = new JwtTokenProvider();
        statics = List.of(mockStatic(Jwts.class, RETURNS_DEEP_STUBS), mockStatic(Keys.class));
    }

    @After
    public void tearDown() {
        statics.forEach(ScopedMock::close);
    }

    @Test
    public void Can_generate_token() {

        final String username = someString(3);
        final String email = someString(5);
        final String token = someString(13);
        final SecretKey secretKey = mock(SecretKey.class);

        // Given
        given(Keys.secretKeyFor(SignatureAlgorithm.HS512)).willReturn(secretKey);
        given(Jwts.builder()
                .setClaims(Map.of("username", username, "email", email))
                .setIssuedAt(any(Date.class))
                .setExpiration(any(Date.class))
                .signWith(secretKey)
                .compact())
                .willReturn(token);

        // When
        final String actual = tokenProvider.generateToken(username, email);

        // Then
        assertThat(actual, is(equalTo(token)));
    }
}
