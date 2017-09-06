package com.support.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Hooks {
    private static WebDriver driver;

    private void initWebDriver(ChromeOptions option) {
        driver = new ChromeDriver(option);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver/chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--window-size=1366,768");
        initWebDriver(option);
    }

    @After
    public void tearDown() {
        try {
            // thread to sleep for 2000 milliseconds
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        }
        getDriver().quit();
    }
}
