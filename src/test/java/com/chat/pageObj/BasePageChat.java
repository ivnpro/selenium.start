package com.chat.pageObj;

import org.openqa.selenium.WebDriver;

import static com.support.helper.Linker.chatLink;
import static org.junit.Assert.assertEquals;

public class BasePageChat {


    protected WebDriver driver;

    public BasePageChat (WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageChat navigateToChat() {
//        driver.navigate().to("http://partners.bloomy-test8.dev.stage.tf/login/");
        driver.navigate().to(chatLink);
        assertEquals("Chat bloomyapp.com", driver.getTitle());
        return new LoginPageChat(driver);
    }
}
