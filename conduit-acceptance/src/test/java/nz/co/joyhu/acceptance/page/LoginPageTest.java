package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.User;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.openqa.selenium.WebElement;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class LoginPageTest {

    private LoginPage page;
    private Browser browser;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        browser = mock(Browser.class);
        baseUrl = someString(5);
        page = new LoginPage(baseUrl, browser);

    }

    @Test
    public void Can_visit_login() {

        // Given


        // When
        page.visit(baseUrl);

        // Then
        then(browser).should().navigate(baseUrl);
    }

    @Test
    public void Can_login_with_user() {

        final User user = mock(User.class);
        final String email = someAlphaString(5) + "@" + someAlphaString(3) + "." + someAlphaString(2);
        final WebElement emailEle = mock(WebElement.class);
        final WebElement passwordEle = mock(WebElement.class);
        final WebElement button = mock(WebElement.class);
        // Given
        given(user.getEmail()).willReturn(email);
        given(user.getPassword()).willReturn(someString(15));
        given(browser.findElementByName("email")).willReturn(emailEle);
        given(browser.findElementByName("password")).willReturn(passwordEle);
        given(browser.findElementByDataTest("sign-in-button")).willReturn(button);

        // When
        page.login(user);

        // Then
        final InOrder order = inOrder(emailEle, passwordEle, button);
        then(emailEle).should(order).sendKeys(user.getEmail());
        then(passwordEle).should(order).sendKeys(user.getPassword());
        then(button).should(order).click();
    }
}