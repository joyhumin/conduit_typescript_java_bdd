package nz.co.joyhu.acceptance.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.joyhu.acceptance.domain.UserFactory;
import nz.co.joyhu.acceptance.page.SignUpPage;
import nz.co.joyhu.acceptance.page.UserHomepage;
import nz.co.joyhu.acceptance.service.AuthenticationService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SignUpSteps {
    private final UserFactory userFactory;
    private final UserHolder userHolder;
    private final SignUpPage signUpPage;
    private final UserHomepage userHomepage;
    private final AuthenticationService authenticationService;

    public SignUpSteps(UserFactory userFactory, UserHolder userHolder, SignUpPage signUpPage, UserHomepage userHomepage, AuthenticationService authenticationService) {
        this.userFactory = userFactory;
        this.userHolder = userHolder;
        this.signUpPage = signUpPage;
        this.userHomepage = userHomepage;
        this.authenticationService = authenticationService;
    }

    @Given("I am a new user")
    public void iAmANewUser() {
        userHolder.set(userFactory.random());
    }

    @And("I am on sign up page")
    public void iAmOnSignUpPage() {
        signUpPage.visit();
    }

    @When("I sign up")
    public void iSignUp() {
        signUpPage.register(userHolder.get());
    }

    @Then("I should be taken to the user homepage")
    public void iShouldBeTakenToTheUserHomepage() {
        assertThat(authenticationService.isRegistered(userHolder.get()), is(true));
        userHomepage.visit();
    }
}
