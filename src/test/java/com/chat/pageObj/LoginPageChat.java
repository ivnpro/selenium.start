package com.chat.pageObj;


import com.support.helper.Girl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.support.helper.WorkerJSON.getGirlByNameFromJSON;

public class LoginPageChat extends BasePageChat{
    public LoginPageChat(WebDriver driver) {
        super(driver);
    }
    // ------------------------------ Locators ------------------------------
    private final By emailChatSelector = By.xpath("//*[@id=\"authorization\"]/div/form/div[1]/input");
    private final By passChatSelector = By.xpath("//*[@id=\"authorization\"]/div/form/div[2]/input");
    private final By btnLoginChat = By.xpath("//*[@id=\"authorization\"]/div/form/div[3]/button");


    public LoginPageChat loginAsGirl(String emailChat, String passChat) {
//        Actions move = new Actions(driver);

        driver.findElement(emailChatSelector).sendKeys(emailChat);
        driver.findElement(passChatSelector).sendKeys(passChat);
        driver.findElement(btnLoginChat).submit();

//        move.click(loginBtn).build().perform();
        return new LoginPageChat(driver);
    }

    public LoginPageChat loginAsGirlObj(String nameGirl, String titleAg) {
//        Actions move = new Actions(driver);
        Girl girl = new Girl();
        girl = getGirlByNameFromJSON(titleAg, nameGirl);
        String loginGirl = girl.getLogin(), passGirl = girl.getPass();

        driver.findElement(emailChatSelector).sendKeys(loginGirl);
        driver.findElement(passChatSelector).sendKeys(passGirl);
        driver.findElement(btnLoginChat).submit();

//        move.click(loginBtn).build().perform();
        return new LoginPageChat(driver);
    }
}
