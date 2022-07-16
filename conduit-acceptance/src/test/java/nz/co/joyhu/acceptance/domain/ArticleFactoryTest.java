package nz.co.joyhu.acceptance.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ArticleFactoryTest {

    private ArticleFactory factory;
    private TagFactory tagFactory;
    private ListFactory listFactory;

    @Before
    public void setUp() throws Exception {
        tagFactory = mock(TagFactory.class);
        listFactory = mock(ListFactory.class);
        factory = new ArticleFactory(listFactory, tagFactory);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_generate_single_article_randomly() {

        // Given
        final List<Tag> tagList = mock(List.class);
        given(tagFactory.randomTags()).willReturn(tagList);

        // When
        final Article actual = factory.random();

        // Then
        assertThat(actual.getFavoritedCount(), notNullValue());
        assertThat(actual.getCreateDate(), notNullValue());
        assertThat(actual.getAuthor(), not(emptyOrNullString()));
        assertThat(actual.getContent(), not(emptyOrNullString()));
        assertThat(actual.getTitle(), not(emptyOrNullString()));
        assertThat(actual.getTagList(), is(tagList));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_generate_random_articles() {

        // Given
        given(listFactory.someOf(any(Supplier.class))).willReturn(mock(List.class));

        // When
        final List<Article> actual = factory.randomArticles();

        // Then
        assertThat(actual, not(empty()));
    }
}