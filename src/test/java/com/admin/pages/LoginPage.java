package com.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    // ------------------------------ Locators ------------------------------
    private final By usernameSelector = By.name("login");
    private final By passwordSelector = By.name("password");
    private final By loginSelector = By.className("col-xs-4");
//    private final By loginBtnSelector = By.xpath("/html/body/div/div[2]/form/div[3]/div/button");

    // ------------------------------ Actions ------------------------------
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public LoginPage loginAsAdmin() {
//        Actions move = new Actions(driver);

        WebElement username = driver.findElement(usernameSelector);
        WebElement password = driver.findElement(passwordSelector);
        WebElement login = driver.findElement(loginSelector);
//        WebElement loginBtn = driver.findElement(loginBtnSelector);

        username.sendKeys("adm");
        password.sendKeys("123");
        login.submit();

//        move.click(loginBtn).build().perform();


        return new LoginPage(driver);
    }
    // ------------------------------ Assertions ------------------------------

}
