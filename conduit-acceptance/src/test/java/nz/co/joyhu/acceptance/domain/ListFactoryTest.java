package nz.co.joyhu.acceptance.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;

public class ListFactoryTest {

    private List<MockedStatic<?>> statics;
    private Random random;
    private ListFactory factory;

    @Before
    public void setUp() throws Exception {
        statics = List.of(
            mockStatic(IntStream.class, RETURNS_DEEP_STUBS)
        );
        random = mock(Random.class);
        factory = new ListFactory(random);
    }

    @After
    public void tearDown() {
        statics.forEach(MockedStatic::close);
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void Can_generate_random_list_of_certain_type() {

        final Integer bound = someIntegerBetween(5, 20);
        final Supplier<Object> mapper = mock(Supplier.class);
        final ArgumentCaptor<IntFunction<Object>> supplierArgumentCaptor = ArgumentCaptor.forClass(IntFunction.class);
        // Given
        given(random.nextInt(20)).willReturn(bound);
        final List expectList = mock(List.class);
        // note: argumentCaptor, if it's hard to mock, check the output
        given(IntStream.range(0, bound).mapToObj(supplierArgumentCaptor.capture()).collect(any(Collector.class))).willReturn(expectList);
        final Object expect = mock(Object.class);
        given(mapper.get()).willReturn(expect);
        // When
        final List<Object> actual = factory.someOf(mapper);

        // Then
        assertThat(actual, is(expectList));
        assertThat(supplierArgumentCaptor.getValue().apply(someIntegerBetween(0, 5)), is(expect));
    }


}