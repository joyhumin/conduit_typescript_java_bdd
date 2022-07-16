package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.Tag;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class TagComponentTest {

    private TagComponent component;
    private Browser browser;

    @Before
    public void setUp() throws Exception {
        browser = mock(Browser.class);
        component = new TagComponent(browser);

    }

    @Test
    public void Can_get_list_of_popular_tags() {

        final WebElement tagBlock = mock(WebElement.class);
        final WebElement tagEle = mock(WebElement.class);
        final List<WebElement> tagList = List.of(tagEle);
        final String tagName = someString(5);
        // Given
        given(browser.findElementByDataTest("popular-tags")).willReturn(tagBlock);
        given(tagBlock.findElements(By.cssSelector("a"))).willReturn(tagList);
        given(tagEle.getText()).willReturn(tagName);

        // When
        final List<Tag> actual = component.getTags();
        final List<String> actualValue = actual.stream().map(Tag::getTagName).collect(toList());

        // Then
        assertThat(actual, hasSize(tagList.size()));
        assertThat(actualValue, contains(tagName));
    }
}