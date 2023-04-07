package nz.co.joyhu.controller;

import nz.co.joyhu.api.v1.*;
import nz.co.joyhu.service.JwtTokenProvider;
import nz.co.joyhu.service.JwtUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class AuthenticationControllerTest {

    private AuthenticationController controller;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private JwtUserDetailsService userDetailsService;

    @Before
    public void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        tokenProvider = mock(JwtTokenProvider.class);
        userDetailsService = mock(JwtUserDetailsService.class);
        controller = new AuthenticationController(authenticationManager, tokenProvider, userDetailsService);

    }

    @Test
    public void Can_register_a_new_user() {

        final NewUserRequest userRequest = mock(NewUserRequest.class);
        final NewUser newUser = mock(NewUser.class);
        final String email = someString(5);
        final UserResponse response = mock(UserResponse.class);
        // Given
        given(userRequest.getUser()).willReturn(newUser);
        given(newUser.getEmail()).willReturn(email);
        given(userDetailsService.findByEmail(email)).willReturn(response);


        // When
        final ResponseEntity<UserResponse> actual = controller.registerNewUser(userRequest);

        // Then
        assertThat(actual.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(actual.getBody(), is(response));

    }

    @Test
    public void Can_login_an_existing_user() {

        final AuthenticationRequest request = mock(AuthenticationRequest.class);
        final AuthenticationRequestDetails requestDetails = mock(AuthenticationRequestDetails.class);
        final String email = someString(13);
        final UserResponse userResponse = mock(UserResponse.class);
        // Given
        given(requestDetails.getEmail()).willReturn(email);
        given(request.getUser()).willReturn(requestDetails);
        given(userDetailsService.findByEmail(email)).willReturn(userResponse);

        // When
        final ResponseEntity<UserResponse> actual = controller.login(request);

        // Then
        assertThat(actual.getStatusCode(), is(equalTo(HttpStatus.OK)));
    }
}
