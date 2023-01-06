package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.selenium.Browser;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class ProfilePage {
    static final String USER_INFO_DATA_TEST = "user-info";
    private final Browser browser;

    public ProfilePage(Browser browser) {

        this.browser = browser;
    }

    public String getUserName() {
        final WebElement userEle = browser.findElementByDataTest(USER_INFO_DATA_TEST);
        return browser.findElementByTag(userEle, "h4").getText();
    }

    public String getUserBioInfo() {
        final WebElement userEle = browser.findElementByDataTest(USER_INFO_DATA_TEST);
        return browser.findElementByTag(userEle, "p").getText();
    }
}
