package com.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FeaturePage extends BasePage{
    public FeaturePage (WebDriver driver) {
        super(driver);
        JavascriptExecutor js = (JavascriptExecutor)driver;
    }

    // ------------------------------ Locators ------------------------------
    private final By turnOnBox = By.xpath("//*[text()='Включено:']/following-sibling::input");

    // ------------------------------ Actions ------------------------------

}
