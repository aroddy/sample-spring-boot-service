package sample.functional;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Cucumber Runner
 */
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, strict = true, format = { "pretty", "html:target/cucumber-html-report" },
        glue = { "net.sf.relish", "sample.functional"}, features = { "classpath:features/" })
public class RunCukes {

}
