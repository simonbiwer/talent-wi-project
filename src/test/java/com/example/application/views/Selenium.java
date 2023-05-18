package com.example.application.views;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mhalah
 * @since 17.05.2023
 * Selenium Test mit Selenuim WebDriver
 */

public class Selenium {
    // WebDrivers für Chrome, Firefox und Edge
    WebDriver chromeDriver;
    WebDriver firefoxDriver;
    WebDriver edgeDriver;

    @BeforeEach
    void setUpChrome() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_win32/chromedriver.exe");
        chromeDriver = new ChromeDriver();
    }

    @BeforeEach
    void setUpFirefox() {
        System.setProperty("webdriver.firefox.driver", "src/test/resources/geckodriver_win32/geckodriver.exe");
        firefoxDriver = new FirefoxDriver();
    }

//    @BeforeEach
//    void setUpEdge() {
//        System.setProperty("webdriver.edge.driver", "Pfad/zum/msedgedriver.exe");
//        edgeDriver = new EdgeDriver();
//    }

    @AfterEach
    void tearDownChrome() {
        chromeDriver.quit();
    }

    @AfterEach
    void tearDownFirefox() {
        firefoxDriver.quit();
    }
//
//    @AfterEach
//    void tearDownEdge() {
//        edgeDriver.quit();
//    }

    @Test
    public void chromeAnmeldeTest() throws InterruptedException {
        loginUndLogoutDurchfuehren(chromeDriver);
    }

    @Test
    public void firefoxAufmeldeTest() throws InterruptedException {
        loginUndLogoutDurchfuehren(firefoxDriver);
    }

//    @Test
//    public void edgeAnmeldenTest() throws InterruptedException {
//        loginUndLogoutDurchfuehren(edgeDriver);
//    }

    private void loginUndLogoutDurchfuehren(@NotNull WebDriver driver) throws InterruptedException {
        driver.get("http://localhost:8080/login");
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement loginFeld = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-vaadin-email-field-6")));
        driver.findElement(By.id("input-vaadin-email-field-6")).sendKeys("plz_dont_delete_this_user@sleniumtest.de");
        driver.findElement(By.id("input-vaadin-password-field-7")).sendKeys("seleNiumtest1234");
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"ROOT-2521314\"]/vaadin-vertical-layout/vaadin-horizontal-layout[2]/vaadin-vertical-layout/vaadin-button")).click();
        Thread.sleep(6000);
        Assertions.assertEquals("http://localhost:8080/", driver.getCurrentUrl());
//        driver.findElement(By.xpath("//*[@id=\"ROOT-2521314\"]/vaadin-app-layout/vaadin-tabs/vaadin-tab[3]")).click();
//        driver.findElement(By.xpath("//*[@id=\"ROOT-2521314\"]/vaadin-app-layout/vaadin-vertical-layout/vaadin-button")).click();
//        Thread.sleep(6000);
//        Assertions.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/login/");

    }
}
