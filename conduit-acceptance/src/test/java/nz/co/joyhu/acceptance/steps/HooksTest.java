package nz.co.joyhu.acceptance.steps;

import io.cucumber.java.Scenario;
import nz.co.joyhu.acceptance.selenium.Browser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomBytes.someBytes;
import static shiver.me.timbers.data.random.RandomStrings.someString;

@RunWith(MockitoJUnitRunner.class)
public class HooksTest {

    private List<GenericHolder> holders;
    private Browser browser;
    private Hooks hooks;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        browser = mock(Browser.class);
        holders = mock(List.class);
        hooks = new Hooks(browser);
        hooks.holders = holders;
    }

    @Test
    public void Setup_does_nothing() {

        // When
        hooks.setup();
    }

    @Test
    public void Tear_down_will_not_log_any_holders_if_the_scenario_passes() {

        final Scenario scenario = mock(Scenario.class);

        // Given
        given(scenario.isFailed()).willReturn(false);

        // When
        hooks.tearDown(scenario);

        // Then
        then(holders).shouldHaveNoInteractions();
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void Tear_down_will_log_all_holders_if_the_scenario_fails() {

        final Scenario scenario = mock(Scenario.class);
        final byte[] bytes = someBytes();
        final String name = someString(7);
//        final ArgumentCaptor<Consumer> captor = ArgumentCaptor.forClass(Consumer.class);

        // Given
        given(scenario.isFailed()).willReturn(true);
        given(scenario.getName()).willReturn(name);
        given(browser.takeScreenShot()).willReturn(bytes);

        willAnswer((Answer<Void>) invocation -> {
            ((Consumer) invocation.getArgument(0)).accept(mock(GenericHolder.class));
            return null;
        }).given(holders).forEach(any(Consumer.class));

        // When
        hooks.tearDown(scenario);

        // Then
        then(scenario).should().attach(bytes, "image/png", name);
        then(browser).should().clear();
        then(holders).should().forEach(any(Consumer.class));
    }
}

