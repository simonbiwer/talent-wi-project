package com.example.application.views;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mhalah
 * @since 17.05.2023
 * Selenium Test mit Selenuim WebDriver
 */

class Selenium {
    WebDriver driver;
    // Launching the Chrome browser
    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login");
    }

    // Login Selenium Training and clicking on it
    @Test
    public void login() throws InterruptedException {
        Duration timeout = Duration.ofSeconds(5); // Set the timeout duration to 5 seconds
        WebDriverWait wait = new WebDriverWait(driver , timeout); // Wait for a maximum of 5 seconds
        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-vaadin-email-field-6")));
        driver.findElement(By.id("input-vaadin-email-field-6")).sendKeys("testtest@email.com");
        driver.findElement(By.id("input-vaadin-password-field-7")).sendKeys("Test1234");
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"ROOT-2521314\"]/vaadin-vertical-layout/vaadin-horizontal-layout[2]/vaadin-vertical-layout/vaadin-button")).click();
        Thread.sleep(6000);
        Assertions.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/");
    }
//    //Logout
//    @Test
//    public void logout() throws InterruptedException {
//        Duration timeout = Duration.ofSeconds(10); // Set the timeout duration to 10 seconds
//        WebDriverWait wait = new WebDriverWait(driver , timeout); // Wait for a maximum of 10 seconds
//        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-vaadin-email-field-6")));
//        driver.findElement(By.id("input-vaadin-email-field-6")).sendKeys("testtest@email.com");
//        driver.findElement(By.id("input-vaadin-password-field-7")).sendKeys("Test1234");
//        Thread.sleep(6000);
//        driver.findElement(By.xpath("//*[@id=\"ROOT-2521314\"]/vaadin-vertical-layout/vaadin-horizontal-layout[2]/vaadin-vertical-layout/vaadin-button")).click();
//        Thread.sleep(6000);
//        wait = new WebDriverWait(driver , timeout);
//        WebElement mainView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("slot")));
//        driver.findElement(By.id("slot")).click();
//        Thread.sleep(6000);
//        driver.findElement(By.xpath("//*[@id=\"ROOT-2521314\"]/vaadin-app-layout/vaadin-tabs/vaadin-tab[3]")).click();
//        driver.findElement(By.xpath("//*[@id=\"ROOT-2521314\"]/vaadin-app-layout/vaadin-vertical-layout/vaadin-button")).click();
//        Thread.sleep(6000);
//        Assertions.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/login/");
//    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
