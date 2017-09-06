package com.agency.pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.support.helper.Linker.agencyLink;
import static org.junit.Assert.assertEquals;

public class BasePageAg{

    // ------------------------------ Agency Locators ------------------------------
    final By headerPageLoc = By.xpath("/html/body/div/div[1]/div[2]/h2");
    final By titleAgLoc = By.xpath("/html/body/div/div[1]/div[1]/h2");
    final By operatorListLoc = By.xpath("/html/body/div/div[2]/div[1]/div[2]/ul/li[5]/a");
    final By addNewGirlLoc = By.xpath("/html/body/div/div[2]/div[1]/div[2]/ul/li[4]");

    protected WebDriver driver;

    public BasePageAg (WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageAg navigateToAgency() {
//        driver.navigate().to("http://partners.bloomy-test8.dev.stage.tf/login/");
        driver.navigate().to(agencyLink);
        assertEquals("Auth", driver.getTitle());
        return new LoginPageAg(driver);
    }

    public LoginPageAg navigateToProdAgency() {
        // ----- АХТУНГ ВЫХОД НА ПРОД -----
        driver.navigate().to("http://partners.bloomyapp.com/login/");
        assertEquals("Auth", driver.getTitle());
        return new LoginPageAg(driver);
    }



}
