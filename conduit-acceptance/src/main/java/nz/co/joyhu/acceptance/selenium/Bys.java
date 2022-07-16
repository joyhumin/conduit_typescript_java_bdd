package nz.co.joyhu.acceptance.selenium;

import nz.co.joyhu.Generated;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class Bys {

    @Generated
    public Bys() {
    }

    public static By dataTest(String dataTest) {
        return attribute("data-test", dataTest);
    }

    public static By attribute(String name, String value) {
        return By.cssSelector(format("[%s=\"%s\"]", name, value));
    }

}
