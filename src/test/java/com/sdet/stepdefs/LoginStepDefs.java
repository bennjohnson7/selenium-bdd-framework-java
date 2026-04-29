package com.sdet.stepdefs;

import com.sdet.pages.LoginPage;
import com.sdet.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.testng.Assert.*;

public class LoginStepDefs {

    private LoginPage loginPage;

    @Before
    public void setup() {
        DriverManager.initDriver();
        loginPage = new LoginPage();
        System.out.println("✅ Browser launched");
    }

    @After
    public void teardown() {
        DriverManager.quitDriver();
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWith(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the products page")
    public void iShouldBeRedirectedToProductsPage() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("inventory"),
                "Not redirected to products page! URL: " + currentUrl);
        System.out.println("✅ Successfully redirected to: " + currentUrl);
    }

    @Then("I should see an error message")
    public void iShouldSeeErrorMessage() {
        assertTrue(loginPage.isErrorDisplayed(),
                "Error message not displayed!");
        System.out.println("✅ Error message: " + loginPage.getErrorMessage());
    }
}