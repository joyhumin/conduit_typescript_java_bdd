package nz.co.joyhu.acceptance.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.ScopedMock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class BrowserTest {

    private Browser browser;
    private WebDriver driver;
    private List<MockedStatic<?>> statics; //wildcard, represents an unknown type

    @Before
    public void setUp() {
        driver = mock(WebDriver.class);
        statics = List.of(mockStatic(Bys.class));
        browser = new Browser(driver);
    }

    @After
    public void tearDown() {
        statics.forEach(ScopedMock::close);
    }

    @Test
    public void Can_navigate_to_url() {

        final String url = someString(5);
        // Given

        // When
        browser.navigate(url);

        // Then
        then(driver).should().get(url); // driver.get(url) same as driver.navigate().to()
    }

    @Test
    public void Can_find_element_by_datatest() {

        final String dataTest = someString(5);
        final By dataTestBy = mock(By.class);
        final WebElement element = mock(WebElement.class);
        // Given
        given(Bys.dataTest(dataTest)).willReturn(dataTestBy);
        given(driver.findElement(dataTestBy)).willReturn(element);

        // When
        final WebElement actual = browser.findElementByDataTest(dataTest);

        // Then
        assertThat(actual, equalTo(element));
    }

    @Test
    public void Can_find_elements_by_data_test() {

        final String datatest = someString(5);
        final By dataTestBy = mock(By.class);
        final List<WebElement> elements = List.of(mock(WebElement.class));
        // Given
        given(Bys.dataTest(datatest)).willReturn(dataTestBy);
        given(driver.findElements(dataTestBy)).willReturn(elements);

        // When
        final List<WebElement> actual = browser.findElementsByDataTest(datatest);

        // Then
        assertThat(actual, equalTo(elements));
    }

    @Test
    public void Can_find_text_of_element_with_data_test() {

        final String dataTest = someString(5);
        final WebElement element = mock(WebElement.class);
        final String expected = someString(11);
        // Given
        given(browser.findElementByDataTest(dataTest)).willReturn(element);
        given(element.getText()).willReturn(expected);

        // When
        final String actual = browser.findTextOfElementWithDataTest(dataTest);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_find_element_by_name() {

        final String name = someAlphaString(5);
        final WebElement expected = mock(WebElement.class);
        // Given
        given(driver.findElement(By.name(name))).willReturn(expected);

        // When
        final WebElement actual = browser.findElementByName(name);

        // Then
        assertThat(actual, is(expected));

    }

    @Test
    public void Can_find_element_by_tag_name() {

        final String tagName = someString(5);
        final WebElement expected = mock(WebElement.class);
        // Given
        given(driver.findElement(By.tagName(tagName))).willReturn(expected);

        // When
        final WebElement actual = browser.findElementByTag(tagName);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_find_element_by_aria_label() {

        final String ariaLabel = someAlphaString(5);
        final WebElement expected = mock(WebElement.class);
        // Given
        given(driver.findElement(Bys.ariaLabel(ariaLabel))).willReturn(expected);

        // When
        final WebElement actual = browser.findElementByAriaLabel(ariaLabel);

        // Then
        assertThat(actual, is(expected));

    }

    @Test
    public void Can_clear_browser_storage_and_cookies() {

        final WebDriver.Options op = mock(WebDriver.Options.class);
        final WebStorage storage = mock(WebStorage.class);
        final SessionStorage sessionStorage = mock(SessionStorage.class);
        final LocalStorage localStorage = mock(LocalStorage.class);
        driver = mock(ChromeDriver.class);
        // Given
        given(driver.manage()).willReturn(op);
//        given((WebStorage) driver).willReturn(storage);
        given(storage.getSessionStorage()).willReturn(sessionStorage);
        given(storage.getLocalStorage()).willReturn(localStorage);

        // When
        browser.clear();

        // Then
        then(op).should().deleteAllCookies();
        then(sessionStorage).should().clear();
        then(localStorage).should().clear();
    }
}