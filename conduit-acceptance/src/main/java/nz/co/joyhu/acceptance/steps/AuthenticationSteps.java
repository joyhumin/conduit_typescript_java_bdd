package nz.co.joyhu.acceptance.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.joyhu.acceptance.domain.UserFactory;
import nz.co.joyhu.acceptance.page.Homepage;
import nz.co.joyhu.acceptance.page.LoginPage;
import nz.co.joyhu.acceptance.page.SignUpPage;
import nz.co.joyhu.acceptance.page.UserHomePage;
import shiver.me.timbers.waiting.Interval;
import shiver.me.timbers.waiting.Timeout;
import shiver.me.timbers.waiting.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticationSteps {
    private final UserFactory userFactory;
    private final UserHolder userHolder;
    private final LoginPage loginPage;
    private final UserHomePage userHomePage;
    private final Homepage homepage;
    private final SignUpPage signUpPage;

    public AuthenticationSteps(UserFactory userFactory, UserHolder userHolder, LoginPage loginPage, UserHomePage userHomePage, Homepage homepage, SignUpPage signUpPage) {
        this.userFactory = userFactory;
        this.userHolder = userHolder;
        this.loginPage = loginPage;
        this.userHomePage = userHomePage;
        this.homepage = homepage;
        this.signUpPage = signUpPage;
    }

    @Given("I am an existing conduit user")
    public void iAmAnExistingConduitUser() {
        userHolder.set(userFactory.randomExistUser());
    }

    @And("I am on login page")
    public void iAmOnLoginPage() {
        loginPage.visit();
    }

    @When("I login when valid credentials")
    public void iLoginWhenValidCredentials() {
        loginPage.login(userHolder.get());
    }

    @Wait
    @Then("I should be taken to personalised feed page")
    public void iShouldBeTakenToPersonalisedFeedPage() {
        // waiting something
        userHomePage.visit();
        assertThat(userHomePage.getUserName(), is(userHolder.get().getUsername()));
    }

    @And("I logged in")
    public void iLoggedIn() {
        loginPage.visit();
        loginPage.login(userHolder.get());
        userHomePage.visit();
    }

    @When("I log out")
    public void iLogOut() {
        userHomePage.logOut();
    }

    @Then("I should be taken to homepage")
    public void iShouldBeTakenToHomepage() {
        homepage.visit();
    }

    @Given("I am a unregsitered user")
    public void iAmAUnregsiteredUser() {
        userHolder.set(userFactory.random());
    }

    @And("I am on sign up page")
    public void iAmOnSignUpPage() {
        signUpPage.visit();
    }

    @When("I register with account information")
    public void iRegisterWithAccountInformation() {
        signUpPage.register(userHolder.get());
    }
}
