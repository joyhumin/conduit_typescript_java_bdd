package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.Tag;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class HomepageTest {

    private Homepage page;
    private String baseUrl;
    private Browser browser;
    private GlobalFeedComponent globalFeedComponent;
    private TagComponent tagComponent;

    @Before
    public void setUp() {

        baseUrl = someString(10);
        browser = mock(Browser.class);
        globalFeedComponent = mock(GlobalFeedComponent.class);
        tagComponent = mock(TagComponent.class);
        page = new Homepage(baseUrl, browser, globalFeedComponent, tagComponent);

    }

    @Test
    public void Can_visit_home_page() {

        // Given

        // When
        page.visit();

        // Then
        then(browser).should().navigate(baseUrl);
    }

    @Test
    public void Can_get_global_feed_articles() {

        final ArticlePreview article = mock(ArticlePreview.class);
        // Given
        given(globalFeedComponent.getArticlePreviews()).willReturn(List.of(article));

        // When
        final List<ArticlePreview> actual = page.getGlobalFeedArticlePreviews();

        // Then
        assertThat(actual, equalTo(List.of(article)));
    }

    @Test
    public void Can_get_list_of_popular_tags() {

        // Given
        final Tag tag = mock(Tag.class);
        given(tagComponent.getTags()).willReturn(List.of(tag));

        // When
        final List<Tag> actual = page.getPopularTags();

        // Then
        assertThat(actual, equalTo(List.of(tag)));
    }

    @Test
    public void Can_get_headline() {

        final String expected = someString(11);
        // Given

        given(browser.findTextOfElementWithDataTest("headline")).willReturn(expected);

        // When
        final String actual = page.getHeadline();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_get_sub_headline() {

        final String expected = someString(11);
        // Given
        given(browser.findTextOfElementWithDataTest("sub-headline")).willReturn(expected);

        // When
        final String actual = page.getSubHeadline();

        // Then
        assertThat(actual, is(expected));
    }
}