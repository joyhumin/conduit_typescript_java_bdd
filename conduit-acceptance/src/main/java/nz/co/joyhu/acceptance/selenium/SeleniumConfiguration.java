package nz.co.joyhu.acceptance.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;
import static io.github.bonigarcia.wdm.config.DriverManagerType.SAFARI;
import static java.lang.String.format;

@Configuration
public class SeleniumConfiguration {

    @Value("${web.driver:chrome}")
    private String webDriver;

    @Bean(destroyMethod = "quit")
    @Scope("singleton")
    public WebDriver webDriver() {
        return chooseDriver();
    }

    private WebDriver chooseDriver() {
        if ("chrome".equals(webDriver)) {
            WebDriverManager.getInstance(CHROME).setup();
            final ChromeOptions options = new ChromeOptions();
            return new ChromeDriver(options);
        }

        if ("chrome-headless".equals(webDriver)) {
            WebDriverManager.getInstance(CHROME).setup();
            final ChromeOptions options = new ChromeOptions();
            applyHeadlessChromeOptions(options);
            return new ChromeDriver(options);
        }

        if ("chrome-docker".equals(webDriver)) {
            WebDriverManager.getInstance(CHROME).setup();
            final ChromeOptions options = new ChromeOptions();
            applyHeadlessChromeOptions(options);
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            return new ChromeDriver(options);
        }

        if ("firefox".equals(webDriver)) {
            WebDriverManager.getInstance(FIREFOX).setup();
            return new FirefoxDriver();
        }

        if ("safari".equals(webDriver)) {
            WebDriverManager.getInstance(SAFARI).setup();
            return new SafariDriver();
        }

        throw new IllegalArgumentException(
            format("Web driver %s not supported.", webDriver)
        );
    }

    @Bean
    public Actions actions(WebDriver driver) {
        return new Actions(driver);
    }

    private static void applyHeadlessChromeOptions(ChromeOptions options) {
        options.addArguments("--headless");
        options.addArguments("--hide-scrollbars");
        options.addArguments("--mute-audio");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
    }
}
