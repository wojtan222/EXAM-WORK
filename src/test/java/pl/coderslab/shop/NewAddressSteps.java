package pl.coderslab.shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class NewAddressSteps {

    private WebDriver driver;

    @Given("I open a web browser with the main page of the online shop")
    public void userIsOnShopMainPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2L));
        driver.get("https://mystore-testlab.coderslab.pl");
        WebElement signInBtn = driver.findElement(By.className("user-info"));
        signInBtn.click();
    }

    @When("I log in with my email and password")
    public void iLogInWithMyEmailAndPassword() {
        driver.findElement(By.cssSelector("#_desktop_user_info a")).click();
        driver.findElement(By.id("field-email")).sendKeys("wojtektest@wp.pl");
        driver.findElement(By.id("field-password")).sendKeys("test123");
        driver.findElement(By.id("submit-login")).click();
    }

    @And("I navigate to the addresses section")
    public void iNavigateToTheAddressesSection() {
        WebElement addressesLink = driver.findElement(By.xpath("//a[contains(text(), 'Addresses')]"));
        addressesLink.click();
    }

    @And("I select the \"Create new address\" option")
    public void iSelectTheOption() {
        driver.findElement(By.xpath("//*[contains(text(), \"Create new address\")]")).click();
    }

    @And("I create new address using {string} alias, {string} address, {string} city, {string} zipCode, {string} phone")
    public void newAddressSheetInputs(String alias, String address, String city, String zipCode, String phone) {
        WebElement aliasInput = driver.findElement(By.id("field-alias"));
        aliasInput.sendKeys(alias);
        WebElement addressInput = driver.findElement(By.id("field-address1"));
        addressInput.sendKeys(address);
        WebElement cityInput = driver.findElement(By.id("field-city"));
        cityInput.sendKeys(city);
        WebElement zipCodeInput = driver.findElement(By.id("field-postcode"));
        zipCodeInput.sendKeys(zipCode);
        WebElement phoneInput = driver.findElement(By.id("field-phone"));
        phoneInput.sendKeys(phone);
    }

    @And("I can see my new address")
    public void iCanSeeMyNewAddress() {
        driver.findElement(By.cssSelector("button.form-control-submit")).click();
    }

    @Then("a new address is successfully added")
    public void aNewAddressIsSuccessfullyAdded() {
        WebElement successAlert = driver.findElement(By.className("alert alert-success"));
        Assertions.assertTrue(successAlert.isDisplayed(), "Success alert should be visible");
        Assertions.assertEquals("Address successfully added!", successAlert.getText(), "'Address successfully added'");
    }

    @And("Checking if {string} expectedAlias, {string} expectedAddress, {string} expectedCity, {string} expectedZipCode, {string} expectedPhone")
    public void fieldsAreEntered(String expectedAlias, String expectedAddress, String expectedCity, String expectedZipCode, String expectedPhone) {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=addresses");
        WebElement newAddress = driver.findElement(By.cssSelector("#address-5124 > div.address-body > address"));
        String newAddressField = newAddress.getText();
        Assertions.assertTrue(newAddressField.contains(expectedAlias));
        Assertions.assertTrue(newAddressField.contains(expectedAddress));
        Assertions.assertTrue(newAddressField.contains(expectedCity));
        Assertions.assertTrue(newAddressField.contains(expectedZipCode));
        Assertions.assertTrue(newAddressField.contains(expectedPhone));
    }

    @And("I close the web browser")
    public void iCloseBrowser() {
        driver.quit();
    }
}