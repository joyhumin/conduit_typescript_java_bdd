package nz.co.joyhu.acceptance.page;

import nz.co.joyhu.acceptance.domain.Tag;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Component
public class TagComponent {
    private final Browser browser;

    public TagComponent(Browser browser) {

        this.browser = browser;
    }

    public List<Tag> getTags() {
        final WebElement popularTag = browser.findElementByDataTest("popular-tags");
        return getTagsWithContext(popularTag);
    }

    public List<Tag> getTagsWithContext(SearchContext searchContext) {
        final List<WebElement> tagElements = searchContext.findElements(By.cssSelector("a"));
        return tagElements.stream()
            .map(createTagFromUI()).collect(toList());
    }

    private Function<WebElement, Tag> createTagFromUI() {
        return ele -> {
            final Tag tag = new Tag();
            tag.setTagName(ele.getText());
            return tag;
        };
    }
}
