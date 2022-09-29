package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;

public class NavigationComponentTest {

    private NavigationComponent component;
    private Browser browser;

    @Before
    public void setUp() throws Exception {
        browser = mock(Browser.class);
        component = new NavigationComponent(browser);
    }

    @Test
    public void Can_get_username_of_nav() {
        final WebElement nav = mock(WebElement.class);
        final WebElement userLink = mock(WebElement.class);
        final String expected = someAlphaString(7);

        // Given
        given(browser.findElementByTag("nav")).willReturn(nav);
        given(browser.findElementByAriaLabel(nav, "user-profile-name")).willReturn(userLink);
        given(userLink.getText()).willReturn(expected);

        // When
        final String actual = component.getUsername();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_log_out() {

        final WebElement nav = mock(WebElement.class);
        final WebElement logOutButton = mock(WebElement.class);
        // Given
        given(browser.findElementByTag("nav")).willReturn(nav);
        given(nav.findElement(By.tagName("button"))).willReturn(logOutButton);

        // When
        component.logOut();

        // Then
        then(logOutButton).should().click();
    }
}