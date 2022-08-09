package nz.co.joyhu.acceptance.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.joyhu.acceptance.domain.Article;
import nz.co.joyhu.acceptance.domain.ArticleFactory;
import nz.co.joyhu.acceptance.domain.TagFactory;
import nz.co.joyhu.acceptance.page.ArticlePreview;
import nz.co.joyhu.acceptance.page.Homepage;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HomepageSteps {
    private final ArticleFactory articleFactory;
    private final ArticleHolder articleHolder;
    private final TagFactory tagFactory;
    private final TagHolder tagHolder;
    private final Homepage homePage;
    

    public HomepageSteps(ArticleFactory articleFactory, ArticleHolder articleHolder, TagFactory tagFactory, TagHolder tagHolder, Homepage homePage) {
        this.articleFactory = articleFactory;
        this.articleHolder = articleHolder;
        this.tagFactory = tagFactory;
        this.tagHolder = tagHolder;
        this.homePage = homePage;
    }

    @Given("the conduit exists")
    public void theConduitExists() {
        // placeholder for the
    }

    @Given("I want to visit conduit homepage")
    public void iWantToVisitConduitHomepage() {
        articleHolder.set(articleFactory.randomArticles());
        tagHolder.set(tagFactory.randomTags());
    }


    @When("I access the homepage")
    public void iAccessTheHomepage() {
        homePage.visit();
    }

    @Then("I can see the banner")
    public void iCanSeeTheBanner() {
        homePage.getHeadline();
        homePage.getSubHeadline();
    }

    // todo: following two steps need to move out from homepage feature file
//    @Then("I can see global feed of articles")
//    public void iCanSeeGlobalFeedOfArticles() {
//        assertGlobalFeed(homePage.getGlobalFeedArticlePreviews(), articleHolder.get());
//    }
//
//    @And("I can see a list of popular tags")
//    public void iCanSeeAListOfPopularTags() {
//        assertThat(homePage.getPopularTags(), equalTo(tagHolder.get()));
//    }

    private void assertGlobalFeed( List<ArticlePreview> actual, List<Article> expected) {
        assertThat(expected.size(), equalTo(actual.size()));
        IntStream.of(actual.size()-1).forEach(
            index -> assertArticlePreview(actual.get(index), expected.get(index))
        );
    }

    private void assertArticlePreview( ArticlePreview actual, Article expected) {
        assertThat(expected.getAuthor(), equalTo(actual.getAuthor()));
        assertThat(expected.getTagList(), equalTo(actual.getTagList()));
        assertThat(expected.getCreateDate(), equalTo(actual.getCreateDate()));
        assertThat(expected.getFavoritedCount(), equalTo(actual.getFavoritedCount()));
        assertThat(expected.getContent().contains(actual.getPreview()), equalTo(true));
    }
}
