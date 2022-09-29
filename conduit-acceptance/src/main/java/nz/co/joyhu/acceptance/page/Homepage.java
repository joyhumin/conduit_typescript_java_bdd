package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.Tag;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Homepage extends BasePage {

    private final GlobalFeedComponent globalFeedComponent;
    private final TagComponent tagComponent;

    public Homepage(@Value("${base.url}") String baseUrl,
                    Browser browser,
                    GlobalFeedComponent globalFeedComponent,
                    TagComponent tagComponent) {
        super(baseUrl, browser);
        this.globalFeedComponent = globalFeedComponent;
        this.tagComponent = tagComponent;
    }


    public List<ArticlePreview> getGlobalFeedArticlePreviews() {
        return globalFeedComponent.getArticlePreviews();
    }

    public List<Tag> getPopularTags() {
        return tagComponent.getTags();
    }

    public String getHeadline() {
        return browser.findTextOfElementWithDataTest("headline");
    }

    public String getSubHeadline() {
        return browser.findTextOfElementWithDataTest("sub-headline");
    }
}
