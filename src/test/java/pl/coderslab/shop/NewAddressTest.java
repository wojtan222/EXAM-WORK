package pl.coderslab.shop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Cucumber/Features",
        glue = "steps")
        public class NewAddressTest{
}
