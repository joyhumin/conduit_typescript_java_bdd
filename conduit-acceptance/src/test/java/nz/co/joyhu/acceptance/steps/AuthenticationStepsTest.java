package nz.co.joyhu.acceptance.steps;

import nz.co.joyhu.acceptance.domain.User;
import nz.co.joyhu.acceptance.domain.UserFactory;
import nz.co.joyhu.acceptance.page.Homepage;
import nz.co.joyhu.acceptance.page.LoginPage;
import nz.co.joyhu.acceptance.page.SignUpPage;
import nz.co.joyhu.acceptance.page.UserHomePage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class AuthenticationStepsTest {

    private AuthenticationSteps steps;
    private UserFactory userFactory;
    private UserHolder userHolder;
    private LoginPage loginPage;
    private UserHomePage userHomePage;
    private Homepage homepage;
    private SignUpPage signUpPage;

    @Before
    public void setUp() throws Exception {
        userFactory = mock(UserFactory.class);
        userHolder = mock(UserHolder.class);
        loginPage = mock(LoginPage.class);
        userHomePage = mock(UserHomePage.class);
        homepage = mock(Homepage.class);
        signUpPage = mock(SignUpPage.class);
        steps = new AuthenticationSteps(userFactory, userHolder, loginPage, userHomePage, homepage, signUpPage);

    }

    @Test
    public void Can_set_up_an_existing_user() {
        final User existUser = mock(User.class);

        // Given
        given(userFactory.randomExistUser()).willReturn(existUser);

        // When
        steps.iAmAnExistingConduitUser();

        // Then
        then(userFactory).should().randomExistUser();
        then(userHolder).should().set(existUser);

    }

    @Test
    public void Can_visit_login_page() {

        // Given

        // When
        steps.iAmOnLoginPage();

        // Then
        then(loginPage).should().visit();
    }

    @Test
    public void Can_login_with_exist_user() {

        final User user = mock(User.class);

        // Given
        given(userHolder.get()).willReturn(user);

        // When
        steps.iLoginWhenValidCredentials();

        // Then
        then(loginPage).should().login(user);
    }

    @Test
    public void Can_visit_personalised_home_page() {
        final String username = someString(5);
        final User user = mock(User.class);

        // Given
        given(userHomePage.getUserName()).willReturn(username);
        given(userHolder.get()).willReturn(user);
        given(user.getUsername()).willReturn(username);

        // When
        steps.iShouldBeTakenToPersonalisedFeedPage();

        // Then
        then(userHomePage).should().getUserName();
    }

    @Test
    public void Can_logged_in_as_existing_user() {

        final User user = mock(User.class);

        // Given
        given(userHolder.get()).willReturn(user);

        // When
        steps.iLoggedIn();

        // Then
        final InOrder order = inOrder(loginPage, userHomePage);
        then(loginPage).should(order).visit();
        then(loginPage).should(order).login(user);
        then(userHomePage).should(order).visit();
    }

    @Test
    public void Can_log_out_user() {

        // Given

        // When
        steps.iLogOut();

        // Then
        then(userHomePage).should().logOut();
    }

    @Test
    public void Can_take_to_homepage_after_logout() {

        // Given

        // When
        steps.iShouldBeTakenToHomepage();

        // Then
        then(homepage).should().visit();
    }

    @Test
    public void Can_have_unregistered_user() {
        final User user = mock(User.class);

        // Given
        given(userFactory.random()).willReturn(user);

        // When
        steps.iAmAUnregsiteredUser();

        // Then
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
    public void Can_register_a_random_user() {

        final User user = mock(User.class);

        // Given
        given(userHolder.get()).willReturn(user);

        // When
        steps.iRegisterWithAccountInformation();

        // Then
        then(signUpPage).should().register(user);
    }
}