package nz.co.joyhu.acceptance.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.ScopedMock;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static shiver.me.timbers.data.random.RandomBytes.someBytes;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class BrowserTest {

    private Browser browser;
    private TestDriver driver;
    private List<MockedStatic<?>> statics; //wildcard, represents an unknown type

    @Before
    public void setUp() {
        driver = mock(TestDriver.class);
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
    public void Can_take_screen_shot() {
        final byte[] bytes = someBytes();

        // Given
        given(driver.getScreenshotAs(OutputType.BYTES)).willReturn(bytes);

        // When
        final byte[] actual = browser.takeScreenShot();

        // Then
        assertThat(actual, is(bytes));
    }

    @Test
    public void Can_clear_browser_storage_and_cookies() {

        final WebDriver.Options op = mock(WebDriver.Options.class);

        // Given
        given(driver.manage()).willReturn(op);
        given(driver.executeScript("localStorage.clear()")).willReturn(any(Object.class));
        given(driver.executeScript("sessionStorage.clear()")).willReturn(any(Object.class));

        // When
        browser.clear();

        // Then
        then(op).should().deleteAllCookies();
        then(driver).should().executeScript("localStorage.clear()");
        then(driver).should().executeScript("sessionStorage.clear()");
    }


    // note: this interface is created for testing purpose
    // so I don't need to test casting
    private interface TestDriver extends WebDriver, JavascriptExecutor, TakesScreenshot {
    }

}