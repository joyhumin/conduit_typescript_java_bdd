package nz.co.joyhu.acceptance.steps;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class SignInStepsTest {

    private SignInSteps steps;

    @Before
    public void setUp() throws Exception {
        steps = new SignInSteps();
    }

}