package nz.co.joyhu.acceptance.selenium;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Browser {
    private final WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate(String baseUrl) {
        driver.get(baseUrl);
    }

    public WebElement findElementByDataTest(String dataTest) {
        return findElementByDataTest(driver, dataTest);
    }

    public WebElement findElementByDataTest(SearchContext parent, String dataTest) {
        return parent.findElement(Bys.dataTest(dataTest));
    }

    public List<WebElement> findElementsByDataTest(String datatest) {
        return findElementsByDataTest(driver, datatest);
    }
    public List<WebElement> findElementsByDataTest(SearchContext parent, String datatest) {
        return parent.findElements(Bys.dataTest(datatest));
    }

    public String findTextOfElementWithDataTest(String dataTest) {
        return findElementByDataTest(dataTest).getText();
    }
}
