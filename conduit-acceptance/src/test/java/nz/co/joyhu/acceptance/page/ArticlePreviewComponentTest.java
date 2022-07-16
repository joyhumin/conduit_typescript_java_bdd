package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.DateTimeFactory;
import nz.co.joyhu.acceptance.domain.Tag;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.time.OffsetDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.data.random.RandomTimes.someTimeLastYear;

public class ArticlePreviewComponentTest {

    private DateTimeFactory dateTimeFactory;
    private ArticlePreviewComponent component;
    private Browser browser;
    private TagComponent tagComponent;

    @Before
    public void setUp() throws Exception {
        dateTimeFactory = mock(DateTimeFactory.class);
        browser = mock(Browser.class, RETURNS_DEEP_STUBS);
        tagComponent = mock(TagComponent.class);
        component = new ArticlePreviewComponent(dateTimeFactory, tagComponent, browser);
    }

    @Test
    public void Can_convert_to_article_preview() {

        final WebElement element = mock(WebElement.class);
        final String author = someString(5);
        final String preview = someString(7);
        final String title = someString(11);
        final Integer integer = someIntegerBetween(0, 9999);
        final String dateTime =
            someTimeLastYear().toInstant().atOffset(OffsetDateTime.now().getOffset()).format(DateTimeFactory.DATE_FORMATTER);
        final WebElement tagGroup = mock(WebElement.class);
        final Tag tag = mock(Tag.class);
        final List<Tag> tagList = List.of(tag);
        // Given
        given(browser.findElementByDataTest(element,"author").getText()).willReturn(author);
        given(browser.findElementByDataTest(element,"preview").getText()).willReturn(preview);
        given(browser.findElementByDataTest(element,"create-date").getText()).willReturn(dateTime);
        given(browser.findElementByDataTest(element,"favorite-count").getText()).willReturn(integer.toString());
        given(browser.findElementByDataTest(element,"title").getText()).willReturn(title);
        given(browser.findElementByDataTest(element, "tag-list")).willReturn(tagGroup);
        given(tagComponent.getTagsWithContext(tagGroup)).willReturn(tagList);

        // When
        final ArticlePreview actual = component.toArticlePreview(element);

        // Then
        assertThat(actual.getPreview(), is(preview));
        assertThat(actual.getTitle(), is(title));
        assertThat(actual.getAuthor(), is(author));
        assertThat(actual.getCreateDate(), is(dateTimeFactory.parseDateFromUI(dateTime)));
        assertThat(actual.getFavoritedCount(), is(integer));
        assertThat(actual.getTagList(), is(tagList));
    }
}