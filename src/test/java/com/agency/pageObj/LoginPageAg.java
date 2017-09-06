package com.agency.pageObj;

import com.support.helper.Agency;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.support.helper.WorkerJSON.getAgencyFromJSON;


public class LoginPageAg extends BasePageAg{
    public LoginPageAg(WebDriver driver) {
        super(driver);
    }
    // ------------------------------ Locators ------------------------------
    private final By emailAgSelector = By.xpath("/html/body/div/div/form/div[1]/div/input");
    private final By passAgSelector = By.xpath("/html/body/div/div/form/div[2]/input");
    private final By btnLoginAg = By.xpath("/html/body/div/div/form/div[3]/input");


    // ------------------------------ Actions ------------------------------


    public LoginPageAg loginAsLadyBugs() {
        driver.findElement(emailAgSelector).sendKeys("fffuuu@tf.co");
        driver.findElement(passAgSelector).sendKeys("00000x");
        driver.findElement(btnLoginAg).submit();

//        move.click(loginBtn).build().perform();
        return new LoginPageAg(driver);
    }

    public LoginPageAg loginAsAgency(String emailAg, String passAg) {
//        Actions move = new Actions(driver);

        driver.findElement(emailAgSelector).sendKeys(emailAg);
        driver.findElement(passAgSelector).sendKeys(passAg);
        driver.findElement(btnLoginAg).submit();

//        move.click(loginBtn).build().perform();
        return new LoginPageAg(driver);
    }

    public LoginPageAg loginAsAgencyFromJSON (String title) {
        Agency agFromJson;
        agFromJson = getAgencyFromJSON(title);

        driver.findElement(emailAgSelector).sendKeys(agFromJson.getEmail());
        driver.findElement(passAgSelector).sendKeys(agFromJson.getPass());
        driver.findElement(btnLoginAg).submit();

        return new LoginPageAg(driver);
    }






    // ------------------------------ Assertions ------------------------------


}
