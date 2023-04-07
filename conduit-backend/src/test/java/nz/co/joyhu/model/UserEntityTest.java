package nz.co.joyhu.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class UserEntityTest {

    private UserEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new UserEntity();
    }

    @Test
    public void Can_create() {
        // Given

        // When
        entity.setBio(someString(5));
        entity.setEmail(someString(5));
        entity.setImage(someString(5));
        entity.setPassword(someString(5));
        entity.setUsername(someString(5));
        // Then
        assertThat(entity.getEmail(), is(notNullValue()));
        assertThat(entity.getImage(), is(notNullValue()));
        assertThat(entity.getUsername(), is(notNullValue()));
        assertThat(entity.getPassword(), is(notNullValue()));
        assertThat(entity.getBio(), is(notNullValue()));

    }
}
