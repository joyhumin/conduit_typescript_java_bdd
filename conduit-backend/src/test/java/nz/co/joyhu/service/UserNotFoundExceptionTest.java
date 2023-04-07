package nz.co.joyhu.service;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class UserNotFoundExceptionTest {

    @Test
    public void Can_create_user_not_found_exeption() {

        // Given
        final String msg = someString(5);

        // When
        final UserNotFoundException actual = new UserNotFoundException(msg);

        // Then
        assertThat(actual.getMessage(), is(equalTo(msg)));
    }
}
