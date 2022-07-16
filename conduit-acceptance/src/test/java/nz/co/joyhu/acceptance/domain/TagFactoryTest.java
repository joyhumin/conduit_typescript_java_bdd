package nz.co.joyhu.acceptance.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class TagFactoryTest {

    private TagFactory factory;
    private ListFactory listFactory;

    @Before
    public void setUp() {
        listFactory = mock(ListFactory.class);
        factory = spy(new TagFactory(listFactory));

    }

    @Test
    public void name() {

        // Given

        // When
        final Tag actual = factory.random();

        // Then
        assertThat(actual.getTagName(), not(emptyOrNullString()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_generate_list_of_random_tags() {

        final Tag tag = mock(Tag.class);
        final List<Tag> tagList = mock(List.class);
        final ArgumentCaptor<Supplier<Tag>> supplierArgumentCaptor = ArgumentCaptor.forClass(Supplier.class);
        // Given
        given(listFactory.someOf(supplierArgumentCaptor.capture())).willReturn(tagList);
        willReturn(tag).given(factory).random(); // note: Sometimes it's impossible or impractical to use when
        // (Object) for stubbing spies. Therefore when using spies please consider doReturn|Answer|Throw() family of
        // methods for stubbing. https://www.javadoc.io/doc/org.mockito/mockito-core/2.7.12/org/mockito/Mockito.html#12

        // When
        final List<Tag> actual = factory.randomTags();
        // Then
        assertThat(actual, is(tagList));
        assertThat(supplierArgumentCaptor.getValue().get(), is(tag));
    }
}