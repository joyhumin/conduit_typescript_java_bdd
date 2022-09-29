package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.User;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.Interval;
import shiver.me.timbers.waiting.Timeout;
import shiver.me.timbers.waiting.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class SignUpPage extends BasePage {

    static final String SIGN_UP_BUTTON = "sign-up-button";
    static final String PASSWORD_INPUT_NAME = "password";
    static final String EMAIL_INPUT_NAME = "email";
    static final String USERNAME_INPUT_NAME = "username";

    public SignUpPage(
        @Value("${base.url}/register")
        String baseUrl,
        Browser browser) {
        super(baseUrl, browser);
    }

    public void register(User user) {
        final WebElement usernameEle = browser.findElementByName(USERNAME_INPUT_NAME);
        final WebElement emailEle = browser.findElementByName(EMAIL_INPUT_NAME);
        final WebElement passwordEle = browser.findElementByName(PASSWORD_INPUT_NAME);
        usernameEle.sendKeys(user.getUsername());
        emailEle.sendKeys(user.getEmail());
        passwordEle.sendKeys(user.getPassword());
        browser.findElementByDataTest(SIGN_UP_BUTTON).click();
    }
}
