import cucumber.api.*;
import cucumber.api.junit.*;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "json:target/cucumber-report.json",
        features = {"features"},
        snippets = SnippetType.CAMELCASE
)
public class RunCucumberTest {

}
