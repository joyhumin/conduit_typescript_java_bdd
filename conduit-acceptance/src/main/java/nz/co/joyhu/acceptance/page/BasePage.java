package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.selenium.Browser;

public abstract class BasePage {
    protected final String baseUrl;
    protected final Browser browser;

    protected BasePage(String baseUrl, Browser browser) {
        this.baseUrl = baseUrl;
        this.browser = browser;
    }

    public void visit() {
        visit(baseUrl);

    }

    public void visit(String url) {
        browser.navigate(url);
    }


}
