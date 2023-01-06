package nz.co.joyhu.acceptance.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.joyhu.acceptance.domain.User;
import nz.co.joyhu.acceptance.page.ProfilePage;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ProfileSteps {
    static final String USER_PROFILE_NAME = "user-profile-name";
    private final Browser browser;
    private final ProfilePage profilePage;
    private final UserHolder userHolder;

    public ProfileSteps(Browser browser, ProfilePage profilePage, UserHolder userHolder) {

        this.browser = browser;
        this.profilePage = profilePage;
        this.userHolder = userHolder;
    }

    @When("I want to visit my profile")
    public void iWantToVisitMyProfile() {
        final WebElement nav = browser.findElementByTag("nav");
        browser.findElementByAriaLabel(nav, USER_PROFILE_NAME).click();
    }

    @Then("I should be taken to user profile page")
    public void iShouldBeTakenToUserProfilePage() {
        final String url = browser.getCurrentUrl();
        final String profileName = url.substring(url.lastIndexOf("/") + 1);
        final User user = userHolder.get();
        assertThat(profileName, is(equalTo(user.getUsername())));

    }

    @And("I should see profile details")
    public void iShouldSeeProfileDetails() {
        final User user = userHolder.get();
        final String profileUserName = profilePage.getUserName();
        final String userBio = profilePage.getUserBioInfo();
        assertThat(profileUserName, is(equalTo(user.getUsername())));
        assertThat(userBio, is(equalTo(user.getBio())));
    }
}
