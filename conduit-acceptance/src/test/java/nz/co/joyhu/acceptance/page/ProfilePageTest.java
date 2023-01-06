package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class ProfilePageTest {

    private ProfilePage page;
    private Browser browser;

    @Before
    public void setUp() throws Exception {
        browser = mock(Browser.class);
        page = new ProfilePage(browser);

    }

    @Test
    public void Can_get_username_from_page() {

        final WebElement userEle = mock(WebElement.class);
        final WebElement userTextEle = mock(WebElement.class);
        final String username = someString(5);
        // Given
        given(browser.findElementByDataTest("user-info")).willReturn(userEle);
        given(browser.findElementByTag(userEle, "h4")).willReturn(userTextEle);
        given(userTextEle.getText()).willReturn(username);

        // When
        final String actual = page.getUserName();

        // Then
        assertThat(actual, is(equalTo(username)));
    }

    @Test
    public void Can_get_user_bio() {

        final WebElement userEle = mock(WebElement.class);
        final WebElement bioEle = mock(WebElement.class);
        final String bio = someString(20);

        // Given
        given(browser.findElementByDataTest("user-info")).willReturn(userEle);
        given(browser.findElementByTag(userEle, "p")).willReturn(bioEle);
        given(bioEle.getText()).willReturn(bio);
        // When
        final String actual = page.getUserBioInfo();

        // Then
        assertThat(actual, is(equalTo(bio)));
    }
}
