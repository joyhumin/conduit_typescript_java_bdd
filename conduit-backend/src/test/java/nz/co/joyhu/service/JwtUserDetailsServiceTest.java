package nz.co.joyhu.service;

import nz.co.joyhu.api.v1.NewUser;
import nz.co.joyhu.api.v1.User;
import nz.co.joyhu.api.v1.UserResponse;
import nz.co.joyhu.model.UserEntity;
import nz.co.joyhu.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class JwtUserDetailsServiceTest {

    private JwtUserDetailsService service;
    private PasswordEncoder encoder;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        encoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);
        service = new JwtUserDetailsService(userRepository, encoder);
    }

    @Test
    public void Can_load_user_by_name() {

        final String username = someString(5);
        final UserEntity userEntity = mock(UserEntity.class);
        final String password = someString(13);

        // Given
        given(userRepository.findByUsername(username)).willReturn(userEntity);
        given(userEntity.getUsername()).willReturn(username);
        given(userEntity.getPassword()).willReturn(password);

        // When
        final UserDetails actual = service.loadUserByUsername(username);

        // Then
        assertThat(actual.getUsername(), is(equalTo(username)));
        assertThat(actual.getPassword(), is(equalTo(password)));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void Can_throw_exception_when_load_user_by_name() {

        final String username = someString(5);

        // Given
        given(userRepository.findByUsername(username)).willReturn(null);

        // When
        service.loadUserByUsername(username);

        // Then
    }

    @Test
    public void Can_save_a_user() {

        final NewUser newUser = mock(NewUser.class);
        final String email = someString(5);
        final String password = someString(7);
        final String userName = someString(3);
        final String encryptedPassword = someString(23);
        final ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);

        // Given
        given(newUser.getEmail()).willReturn(email);
        given(newUser.getPassword()).willReturn(password);
        given(newUser.getUsername()).willReturn(userName);
        given(encoder.encode(password)).willReturn(encryptedPassword);

        // When
        service.save(newUser);

        // Then
        then(userRepository).should().save(userCaptor.capture());
        final UserEntity user = userCaptor.getValue();
        assertThat(user.getEmail(), is(equalTo(email)));
        assertThat(user.getPassword(), is(equalTo(encryptedPassword)));
    }

    @Test
    public void Can_find_by_email() {

        final String email = someString(5);
        final UserEntity userEntity = mock(UserEntity.class);
        final String username = someString(3);
        final String bio = someString(21);
        final String image = someString(7);
        // Given
        given(userRepository.findByEmail(email)).willReturn(userEntity);
        given(userEntity.getUsername()).willReturn(username);
        given(userEntity.getBio()).willReturn(bio);
        given(userEntity.getImage()).willReturn(image);
        given(userEntity.getEmail()).willReturn(email);

        // When
        final UserResponse actual = service.findByEmail(email);

        // Then
        final User user = actual.getUser();
        assertThat(user.getEmail(), is(equalTo(email)));
        assertThat(user.getBio(), is(equalTo(bio)));
        assertThat(user.getImage(), is(equalTo(image)));
        assertThat(user.getUsername(), is(equalTo(username)));
    }

    @Test(expected = UserNotFoundException.class)
    public void Can_throw_exception_when_find_by_email() {

        final String email = someString(5);
        // Given
        given(userRepository.findByEmail(email)).willReturn(null);

        // When
        service.findByEmail(email);

        // Then
    }
}
