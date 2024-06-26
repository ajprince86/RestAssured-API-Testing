package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        glue={"stepDefinitions"},
        plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner {
}
//        ,tags="@DeletePlace"