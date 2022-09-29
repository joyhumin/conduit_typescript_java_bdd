package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.selenium.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class NavigationComponent {
    static final String COMPONENT_TAG_NAME = "nav";
    private final Browser browser;

    public NavigationComponent(Browser browser) {

        this.browser = browser;
    }

    public String getUsername() {
        final WebElement nav = browser.findElementByTag(COMPONENT_TAG_NAME);
        return browser.findElementByAriaLabel(nav, "user-profile-name").getText();
    }

    public void logOut() {
        final WebElement nav = browser.findElementByTag(COMPONENT_TAG_NAME);
        nav.findElement(By.tagName("button")).click();
    }
}
