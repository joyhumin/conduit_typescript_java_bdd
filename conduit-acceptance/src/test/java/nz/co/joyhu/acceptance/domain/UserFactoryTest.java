package nz.co.joyhu.acceptance.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class UserFactoryTest {

    private UserFactory userFactory;

    @Before
    public void setUp() {
        userFactory = new UserFactory();
    }

    @Test
    public void Can_generate_exist_user() {

        // Given

        // When
        final User actual = userFactory.randomExistUser();

        // Then
        assertThat(actual.getEmail(), is("test@gmail.com"));
        assertThat(actual.getPassword(), is("test"));
        assertThat(actual.getUsername(), is("test"));

    }

    @Test
    public void Can_random_generate_user() {

        // Given

        // When
        final User actual = userFactory.random();

        // Then
        assertThat(actual.getUsername(), is(notNullValue()));
        assertThat(actual.getEmail(), matchesPattern(".+@.+[.].+"));
        assertThat(actual.getPassword(), is(notNullValue()));
    }
}