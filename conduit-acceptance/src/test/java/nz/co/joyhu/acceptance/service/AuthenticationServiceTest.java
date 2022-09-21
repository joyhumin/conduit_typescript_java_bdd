package nz.co.joyhu.acceptance.service;

import nz.co.joyhu.acceptance.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AuthenticationServiceTest {

    private AuthenticationService service;

    @Before
    public void setUp() throws Exception {
        service = new AuthenticationService();
    }

    @Test
    public void Can_verify_if_a_user_is_registered() {

        final User user = mock(User.class);
        // Given

        // When
        service.isRegistered(user);

        // Then
    }
}