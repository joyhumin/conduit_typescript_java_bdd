package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.User;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.openqa.selenium.WebElement;

import static nz.co.joyhu.acceptance.page.SignUpPage.EMAIL_INPUT_NAME;
import static nz.co.joyhu.acceptance.page.SignUpPage.PASSWORD_INPUT_NAME;
import static nz.co.joyhu.acceptance.page.SignUpPage.SIGN_UP_BUTTON;
import static nz.co.joyhu.acceptance.page.SignUpPage.USERNAME_INPUT_NAME;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class SignUpPageTest {

    private SignUpPage page;
    private Browser browser;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        browser = mock(Browser.class);
        baseUrl = someString(5);
        page = new SignUpPage(baseUrl, browser);

    }

    @Test
    public void Can_visit_signup_page() {

        // Given

        // When
        page.visit();

        // Then
        then(browser).should().navigate(baseUrl);

    }

    @Test
    public void Can_sign_up_a_user() {

        final User user = mock(User.class);
        final String password = someString(11);
        final String email = someString(11);
        final String username = someString(11);
        final WebElement usernameEle = mock(WebElement.class);
        final WebElement passwordEle = mock(WebElement.class);
        final WebElement emailEle = mock(WebElement.class);
        final WebElement button = mock(WebElement.class);
        // Given
        given(user.getPassword()).willReturn(password);
        given(user.getEmail()).willReturn(email);
        given(user.getUsername()).willReturn(username);

        given(browser.findElementByName(USERNAME_INPUT_NAME)).willReturn(usernameEle);
        given(browser.findElementByName(EMAIL_INPUT_NAME)).willReturn(emailEle);
        given(browser.findElementByName(PASSWORD_INPUT_NAME)).willReturn(passwordEle);
        given(browser.findElementByDataTest(SIGN_UP_BUTTON)).willReturn(button);

        // When
        page.register(user);

        // Then
        final InOrder order = inOrder(usernameEle, emailEle, passwordEle, button);
        then(usernameEle).should(order).sendKeys(username);
        then(emailEle).should(order).sendKeys(email);
        then(passwordEle).should(order).sendKeys(password);
        then(button).should(order).click();

    }
}