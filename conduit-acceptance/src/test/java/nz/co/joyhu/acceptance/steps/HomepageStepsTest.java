package nz.co.joyhu.acceptance.steps;

import nz.co.joyhu.acceptance.domain.Article;
import nz.co.joyhu.acceptance.domain.ArticleFactory;
import nz.co.joyhu.acceptance.domain.Tag;
import nz.co.joyhu.acceptance.domain.TagFactory;
import nz.co.joyhu.acceptance.page.ArticlePreview;
import nz.co.joyhu.acceptance.page.Homepage;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class HomepageStepsTest {

    private HomepageSteps steps;
    private Homepage homePage;
    private ArticleFactory articleFactory;
    private ArticleHolder articleHolder;
    private TagFactory tagFactory;
    private TagHolder tagHolder;

    @Before
    public void setUp(){
        homePage = mock(Homepage.class);
        articleFactory = mock(ArticleFactory.class);
        articleHolder = mock(ArticleHolder.class);
        tagFactory = mock(TagFactory.class);
        tagHolder = mock(TagHolder.class);
        steps = new HomepageSteps(
            articleFactory,
            articleHolder,
            tagFactory,
            tagHolder,
            homePage
        );
    }

    @Test
    public void Test_coverage_for_website_exists() {

        // Given

        // When
        steps.theConduitExists();

        // Then
    }

    @Test
    @SuppressWarnings("unchecked")
    public void I_want_to_visit_conduit_homepage() {

        final Article article = mock(Article.class);
        // Given
        given(articleFactory.randomArticles()).willReturn(List.of(article));
        final List<Tag> tags = mock(List.class);
        given(tagFactory.randomTags()).willReturn(tags);

        // When
        steps.iWantToVisitConduitHomepage();

        // Then
        then(articleFactory).should().randomArticles();
        then(tagFactory).should().randomTags();
        then(articleHolder).should().set(List.of(article));
        then(tagHolder).should().set(tags);
    }

    @Test
    public void Can_access_to_homepage() {

        // Given

        // When
        steps.iAccessTheHomepage();

        // Then
        then(homePage).should().visit();
    }

    @Test
    public void Can_see_the_banner() {

        // Given

        // When
        steps.iCanSeeTheBanner();

        // Then
        then(homePage).should().getHeadline();
        then(homePage).should().getSubHeadline();
    }

//    @Test
//    public void Can_see_a_global_feed_of_articles() {
//
//        final ArticlePreview preview = mock(ArticlePreview.class);
//        final List<ArticlePreview> previews = List.of(preview);
//        final Article article = mock(Article.class);
//        final String author = someString(5);
//        final OffsetDateTime createDate = OffsetDateTime.now();
//        final Integer count = someIntegerBetween(0, 9999);
//        final String previewContent = someString(5);
//        final List<Tag> tagList = List.of(mock(Tag.class));
//
//        // Given
//        given(articleHolder.get()).willReturn(List.of(article));
//        given(homePage.getGlobalFeedArticlePreviews()).willReturn(previews);
//        given(article.getAuthor()).willReturn(author);
//        given(article.getCreateDate()).willReturn(createDate);
//        given(article.getFavoritedCount()).willReturn(count);
//        given(article.getContent()).willReturn(previewContent);
//        given(article.getTagList()).willReturn(tagList);
//
//        given(preview.getAuthor()).willReturn(author);
//        given(preview.getCreateDate()).willReturn(createDate);
//        given(preview.getFavoritedCount()).willReturn(count);
//        given(preview.getPreview()).willReturn(previewContent);
//        given(preview.getTagList()).willReturn(tagList);
//
//        // When
//        steps.iCanSeeGlobalFeedOfArticles();
//
//        // Then
//        then(articleHolder).should().get();
//        then(homePage).should().getGlobalFeedArticlePreviews();
//    }
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void Can_see_a_list_of_popular_tags() {
//
//        final List<Tag> tags = mock(List.class);
//        // Given
//        given(homePage.getPopularTags()).willReturn(tags);
//        given(tagHolder.get()).willReturn(tags);
//
//        // When
//        steps.iCanSeeAListOfPopularTags();
//
//        // Then
//        then(homePage).should().getPopularTags();
//        then(tagHolder).should().get();
//    }
}