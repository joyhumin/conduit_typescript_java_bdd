package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class UserHomePageTest {

    private UserHomePage page;
    private Browser browser;
    private String baseUrl;
    private NavigationComponent nav;

    @Before
    public void setUp() throws Exception {
        browser = mock(Browser.class);
        baseUrl = someString(11);
        nav = mock(NavigationComponent.class);
        page = new UserHomePage(baseUrl, browser, nav);

    }

    @Test
    public void Can_visit_user_homepage() {

        // Given

        // When
        page.visit();

        // Then
        then(browser).should().navigate(baseUrl);

    }

    @Test
    public void Can_get_username_of_user_homepage() {

        // Given
        final String expected = someString(5);
        given(nav.getUsername()).willReturn(expected);

        // When
        final String actual = page.getUserName();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_log_out_user() {

        // Given

        // When
        page.logOut();

        // Then
        then(nav).should().logOut();
    }
}