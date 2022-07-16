package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.Tag;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GlobalFeedComponentTest {

    private GlobalFeedComponent component;
    private Browser browser;
    private Object articlePreview;
    private ArticlePreviewComponent previewComponent;

    @Before
    public void setUp() {
        browser = mock(Browser.class);
        previewComponent = mock(ArticlePreviewComponent.class);
        component = new GlobalFeedComponent(browser, previewComponent);

    }

    @Test
    public void Can_get_articles() {

        final WebElement globalFeed = mock(WebElement.class);
        final WebElement articlePreview = mock(WebElement.class);
        final List<WebElement> articleList = List.of(articlePreview);
        final Tag tag = mock(Tag.class);
        final ArticlePreview expected = mock(ArticlePreview.class);

        // Given
        given(browser.findElementByDataTest("article-global-feed")).willReturn(globalFeed);
        given(browser.findElementsByDataTest(globalFeed,"article-preview")).willReturn(articleList);
        given(previewComponent.toArticlePreview(articlePreview)).willReturn(expected);
        // When
        final List<ArticlePreview> actual = component.getArticlePreviews();

        // Then
        assertThat(actual.size(), equalTo(articleList.size()));
        final ArticlePreview article = actual.get(0);
        assertThat(article, equalTo(expected));
    }
}