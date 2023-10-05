package pl.coderslab.shop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\CodersLab\\EXAM-WORK\\src\\Cucumber\\Features\\new-addrress-infomation.feature",
        glue = "pl.coderslab.shop", //
        plugin = {"pretty", "html:target/cucumber-reports"}
)

public class NewAddressTest {

}
