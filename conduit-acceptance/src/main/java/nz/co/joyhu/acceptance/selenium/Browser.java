package nz.co.joyhu.acceptance.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
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

    public WebElement findElementByName(String name) {
        return driver.findElement(By.name(name));
    }

    public WebElement findElementByTag(String tagName) {
        return findElementByTag(driver, tagName);
    }

    public WebElement findElementByTag(SearchContext parent, String tagName) {
        return parent.findElement(By.tagName(tagName));
    }

    public WebElement findElementByAriaLabel(String ariaLabel) {
        return findElementByAriaLabel(driver, ariaLabel);
    }

    public WebElement findElementByAriaLabel(SearchContext parent, String ariaLabel) {
        return parent.findElement(Bys.ariaLabel(ariaLabel));
    }

    public byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void clear() {
        clearStorage();
        deleteALlCookies();
    }

    public void deleteALlCookies() {
        driver.manage().deleteAllCookies();
    }

    public void clearStorage() {
        final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        final WebStorage webStorage = (WebStorage) driver; note: webStorage interface only be implemented in chromeDriver & firefox driver.
        jsExecutor.executeScript("localStorage.clear()");
        jsExecutor.executeScript("sessionStorage.clear()");
    }
}
