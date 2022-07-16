package nz.co.joyhu.acceptance.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

import static nz.co.joyhu.acceptance.domain.DateTimeFactory.DATE_FORMATTER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class DateTimeFactoryTest {

    private List<MockedStatic<?>> statics;
    private DateTimeFactory factory;

    @Before
    public void setUp()  {
        statics = List.of(
            mockStatic(OffsetDateTime.class, RETURNS_DEEP_STUBS), // note: deep stub can be used for chain method so
            // don't need to write long given
            mockStatic(LocalDate.class, RETURNS_DEEP_STUBS)
        );

        factory = new DateTimeFactory();
    }

    @After
    public void tearDown()  {
        statics.forEach(MockedStatic::close);
    }

    @Test
    public void Can_parse_date_string_to_offset_datetime() {

        final String date = someString(7);
        final OffsetDateTime expect = mock(OffsetDateTime.class);

        // Given
        given(LocalDate.parse(date, DATE_FORMATTER)
            .atStartOfDay(ZoneId.systemDefault())
            .toOffsetDateTime()).willReturn(expect); // if we deep stub, we won't rely on the actual implementation
        // of the date time class

        // When
        final OffsetDateTime actual = factory.parseDateFromUI(date);

        // Then
        assertThat(actual, is(expect));
    }
}