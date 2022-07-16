package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.selenium.Browser;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class GlobalFeedComponent {
    private final Browser browser;
    private final ArticlePreviewComponent previewComponent;

    public GlobalFeedComponent(Browser browser, ArticlePreviewComponent previewComponent) {
        this.browser = browser;
        this.previewComponent = previewComponent;
    }

    public List<ArticlePreview> getArticlePreviews() {
        final WebElement globalFeed = browser.findElementByDataTest("article-global-feed");
        final List<WebElement> articlePreviews = browser.findElementsByDataTest(globalFeed, "article-preview");
        return articlePreviews.stream().map(previewComponent::toArticlePreview).collect(toList());
    }
}
