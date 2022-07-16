package nz.co.joyhu.acceptance.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class BysTest {

    @Test
    public void Can_create_a_data_test_by() {

        final String dataTest = someString(5);
        // Given

        // When
        final By actual = Bys.dataTest(dataTest);

        // Then
        assertThat(actual, equalTo(By.cssSelector(format("[data-test=\"%s\"]", dataTest))));
    }
}