package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.User;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    public LoginPage(@Value("${base.url}/login") String baseUrl, Browser browser) {
        super(baseUrl, browser);
    }


    public void login(User user) {
        final WebElement emailInput = browser.findElementByName("email");
        final WebElement passwordInput = browser.findElementByName("password");
        final WebElement button = browser.findElementByDataTest("sign-in-button");
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        button.click();
    }
}
