package nz.co.joyhu.acceptance.steps;

import nz.co.joyhu.acceptance.domain.User;
import nz.co.joyhu.acceptance.domain.UserFactory;
import nz.co.joyhu.acceptance.page.SignUpPage;
import nz.co.joyhu.acceptance.page.UserHomepage;
import nz.co.joyhu.acceptance.service.AuthenticationService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class SignUpStepsTest {

    private SignUpSteps steps;
    private UserHolder userHolder;
    private UserFactory userFactory;
    private SignUpPage signUpPage;
    private AuthenticationService authenticationService;
    private UserHomepage userHomepage;

    @Before
    public void setUp() {
        userHolder = mock(UserHolder.class);
        userFactory = mock(UserFactory.class);
        signUpPage = mock(SignUpPage.class);
        authenticationService = mock(AuthenticationService.class);
        userHomepage = mock(UserHomepage.class);
        steps = new SignUpSteps(userFactory,userHolder, signUpPage, userHomepage,authenticationService);
    }

    @Test
    public void Can_generate_a_new_user() {

        final User user = mock(User.class);
        // Given
        given(userFactory.random()).willReturn(user);

        // When
        steps.iAmANewUser();

        // Then
        then(userFactory).should().random();
        then(userHolder).should().set(user);
    }

    @Test
    public void Can_visit_sign_up_page() {

        // Given

        // When
        steps.iAmOnSignUpPage();

        // Then
        then(signUpPage).should().visit();
    }

    @Test
    public void Can_sign_up_a_new_user() {

        final User user = mock(User.class);
        // Given
        given(userHolder.get()).willReturn(user);

        // When
        steps.iSignUp();

        // Then
        then(signUpPage).should().register(user);
    }

    @Test
    public void Can_take_to_user_homepage_after_login() {

        final User user = mock(User.class);
        // Given
        given(userHolder.get()).willReturn(user);
        given(authenticationService.isRegistered(user)).willReturn(true);

        // When
        steps.iShouldBeTakenToTheUserHomepage();

        // Then
        then(userHomepage).should().visit();
    }
}