package com.sdet.pages;

import com.sdet.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click element with explicit wait
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        System.out.println("✅ Clicked: " + locator);
    }

    // Type text into element
    protected void type(By locator, String text) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        System.out.println("✅ Typed '" + text + "' into: " + locator);
    }

    // Get text from element
    protected String getText(By locator) {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Check if element is visible
    protected boolean isVisible(By locator) {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator))
                .isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }

    // Navigate to URL
    protected void navigateTo(String url) {
        driver.get(url);
        System.out.println("✅ Navigated to: " + url);
    }
}