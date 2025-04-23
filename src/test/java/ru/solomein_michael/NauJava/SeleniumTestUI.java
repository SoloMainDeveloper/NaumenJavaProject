package ru.solomein_michael.NauJava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTestUI {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSuccessfulLoginAndLogout() {
        driver.get("http://localhost:8080/login");

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.className("primary"));

        usernameField.sendKeys("admin");
        passwordField.sendKeys("999");
        loginButton.click();
        sleep(2000);

        assertTrue(driver.getCurrentUrl().contains("/home"));
        assertTrue(driver.findElement(By.className("Hello")).isDisplayed());

        WebElement logoutButton = driver.findElement(By.className("logoutButton"));
        logoutButton.click();
        sleep(2000);
        WebElement logoutButton2 = driver.findElement(By.className("primary"));
        logoutButton2.click();
        sleep(2000);

        assertTrue(driver.getCurrentUrl().contains("/login"));
        assertTrue(driver.findElement(By.className("login-form")).isDisplayed());
        driver.quit();
    }
}
