package nz.co.joyhu.acceptance.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.ScopedMock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
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
}