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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isItGreenAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        String isItGreenAlertText = isItGreenAlert.getText();
        Assertions.assertEquals("Address successfully added!", isItGreenAlertText, "It should say 'Address successfully added!' ");
    }


    @And("I verify the newly created address details {string} expectedAlias, {string} expectedAddress, {string} expectedCity, {string} expectedZipCode, {string} expectedPhone")
    public void iVerifyNewAddressSheet(String expectedAlias, String expectedAddress, String expectedCity, String expectedZipCode, String expectedPhone) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement addressField = driver.findElement(By.xpath("//section[@id='wrapper']"));
        String addressFieldText = addressField.getText();

        Assertions.assertTrue(addressFieldText.contains(expectedAlias));
        Assertions.assertTrue(addressFieldText.contains(expectedAddress));
        Assertions.assertTrue(addressFieldText.contains(expectedCity));
        Assertions.assertTrue(addressFieldText.contains(expectedZipCode));
        Assertions.assertTrue(addressFieldText.contains(expectedPhone));
        Assertions.assertTrue(
                addressFieldText.contains(expectedAlias),
                "Expected alias not found in the address field."
        );
    }


    @And("I close the web browser")
    public void iCloseBrowser() {
        driver.quit();
    }
}