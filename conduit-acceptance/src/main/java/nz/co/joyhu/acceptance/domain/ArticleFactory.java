package nz.co.joyhu.acceptance.domain;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.data.random.RandomTimes.someTimeLastYear;

@Component
public class ArticleFactory {
    private final ListFactory listFactory;
    private final TagFactory tagFactory;

    public ArticleFactory(ListFactory listFactory, TagFactory tagFactory) {
        this.listFactory = listFactory;
        this.tagFactory = tagFactory;
    }

    public Article random() {
        final Article article = new Article();
        article.setAuthor(someString(7));
        article.setContent(someString());
        article.setTitle(someString(11));
        final OffsetDateTime createDate = someTimeLastYear().toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime();
        article.setCreateDate(createDate);
        article.setFavoritedCount(someIntegerBetween(0, 9999));
        article.setTagList(tagFactory.randomTags());
        return article;
    }

    public List<Article> randomArticles() {
        return listFactory.someOf(this::random);
    }
}
