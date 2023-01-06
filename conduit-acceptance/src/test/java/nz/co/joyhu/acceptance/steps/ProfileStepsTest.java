package nz.co.joyhu.acceptance.steps;

import nz.co.joyhu.acceptance.domain.User;
import nz.co.joyhu.acceptance.page.ProfilePage;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someAlphanumericString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class ProfileStepsTest {

    private ProfileSteps steps;
    private Browser browser;
    private ProfilePage profilePage;
    private UserHolder userHolder;

    @Before
    public void setUp() throws Exception {
        browser = mock(Browser.class);
        profilePage = mock(ProfilePage.class);
        userHolder = mock(UserHolder.class);
        steps = new ProfileSteps(browser, profilePage, userHolder);

    }

    @Test
    public void Can_visit_profile_page_by_click_button() {

        final WebElement usernameEle = mock(WebElement.class);
        final WebElement nav = mock(WebElement.class);

        // Given
        given(browser.findElementByTag("nav")).willReturn(nav);
        given(browser.findElementByAriaLabel(nav, "user-profile-name")).willReturn(usernameEle);

        // When
        steps.iWantToVisitMyProfile();

        // Then
        then(usernameEle).should().click();
    }

    @Test
    public void Can_verify_profile_page_url() {

        final String username = someAlphanumericString();
        final String url = someString(11) + "/profile/" + username;
        final User user = mock(User.class);

        // Given
        given(browser.getCurrentUrl()).willReturn(url);
        given(userHolder.get()).willReturn(user);
        given(user.getUsername()).willReturn(username);

        // When
        steps.iShouldBeTakenToUserProfilePage();

        // Then
        then(userHolder).should().get();
        then(browser).should().getCurrentUrl();
    }

    @Test
    public void Can_verify_user_profile_details() {

        final User user = mock(User.class);
        final String username = someAlphanumericString(11);
        final String bio = someString(20);
        // Given
        given(userHolder.get()).willReturn(user);
        given(user.getUsername()).willReturn(username);
        given(user.getBio()).willReturn(bio);
        given(profilePage.getUserName()).willReturn(username);
        given(profilePage.getUserBioInfo()).willReturn(bio);

        // When
        steps.iShouldSeeProfileDetails();

        // Then
        then(userHolder).should().get();
    }
}
