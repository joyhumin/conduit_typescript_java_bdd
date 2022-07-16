package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.DateTimeFactory;
import nz.co.joyhu.acceptance.domain.Tag;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticlePreviewComponent {

    private final DateTimeFactory dateTimeFactory;
    private final TagComponent tagComponent;
    private final Browser browser;

    public ArticlePreviewComponent(DateTimeFactory dateTimeFactory, TagComponent tagComponent, Browser browser) {
        this.dateTimeFactory = dateTimeFactory;
        this.tagComponent = tagComponent;
        this.browser = browser;
    }

    public ArticlePreview toArticlePreview(WebElement articlePreviewElement) {
        final ArticlePreview articlePreview = new ArticlePreview();
        articlePreview.setPreview(browser.findElementByDataTest(articlePreviewElement,"preview").getText());
        articlePreview.setAuthor(browser.findElementByDataTest(articlePreviewElement,"author").getText());
        articlePreview.setTitle(browser.findElementByDataTest(articlePreviewElement,"title").getText());
        final String countString = browser.findElementByDataTest(articlePreviewElement,"favorite-count").getText();
        articlePreview.setFavoritedCount(Integer.parseInt(countString));
        final List<Tag> tags = tagComponent.getTagsWithContext(browser.findElementByDataTest(articlePreviewElement,
            "tag-list"));
        articlePreview.setTagList(tags);
        final String date = browser.findElementByDataTest("create-date").getText();
        articlePreview.setCreateDate(dateTimeFactory.parseDateFromUI(date));
        return articlePreview;
    }

}
