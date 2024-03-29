package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.selenium.Browser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.Decision;
import shiver.me.timbers.waiting.Interval;
import shiver.me.timbers.waiting.Wait;

import java.util.concurrent.TimeUnit;

@Component
public class UserHomePage extends BasePage {

    private final NavigationComponent nav;

    public UserHomePage(
        @Value("${base.url}") String baseUrl,
        Browser browser, NavigationComponent nav) {
        super(baseUrl, browser);
        this.nav = nav;
    }

    @Override
    public void visit() {
        browser.navigate(baseUrl);
    }

    @Wait(waitForNotNull = Decision.YES, interval = @Interval(unit = TimeUnit.SECONDS, duration = 2L))
    public String getUserName() {
        return nav.getUsername();
    }

    public void logOut() {
        nav.logOut();
    }
}
