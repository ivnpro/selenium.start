package com.admin.pages;

import org.openqa.selenium.WebDriver;

import static com.support.helper.Linker.adminLink;
import static org.junit.Assert.assertEquals;

public class BasePage {

    protected WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public LoginPage navigateToAdm() {
//        driver.navigate().to("http://admin.bloomy-test8.dev.stage.tf");
        driver.navigate().to(adminLink);
        assertEquals("Bloomy admin", driver.getTitle());
        return new LoginPage(driver);
    }

}
