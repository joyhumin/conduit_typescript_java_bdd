package nz.co.joyhu.acceptance.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import nz.co.joyhu.ITCucumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@CucumberContextConfiguration
@SpringBootTest(classes = ITCucumber.class, webEnvironment = NONE)
public class Hooks {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    @SuppressWarnings("rawtypes")
    private List<GenericHolder> holders = emptyList();

    @Before
    public void setup(){
//        execute before every scenarios
        log.info("Scenario Start.");
    }

    @After
    public void tearDown(Scenario scenario){
//        execute after every scenario, regardless whether the scenario finished successfully.
        log.info("Scenario End.");
        if (scenario.isFailed()) {
            holders.forEach(holder -> log.error(holder.toString()));
        }
    }
}
